package com.arch.micro_service.auth_server.user.application.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.arch.micro_service.auth_server.role.application.usecase.role.RoleFindUseCase;
import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.user.application.constant.UserCrudConstant;
import com.arch.micro_service.auth_server.user.application.service.UserCrudService;
import com.arch.micro_service.auth_server.user.application.usecase.UserFindUseCase;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.vo.Password;
import com.arch.micro_service.auth_server.user.infrastructure.dto.mapper.UserMapper;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserCreateRequest;
import com.arch.micro_service.auth_server.user.infrastructure.persistence.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCrudServiceImpl implements UserCrudService {

  private final UserRepository userRepository;
  private final UserFindUseCase userFindUseCase;
  private final RoleFindUseCase roleFindUseCase;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public List<User> getAll() {
    var users = userRepository
        .findAll()
        .stream()
        .filter(p -> !p.isDeleted())
        .toList();

    return users;
  }

  @Override
  public User get(String id) {
    return userFindUseCase.findById(id);
  }

  @Override
  public User create(UserCreateRequest requestDto) {

    User user = userMapper.toUser(requestDto);
    user.updatePassword(passwordEncoder.encode(requestDto.password()));

    if (requestDto.roles() != null) {
      List<Role> roles = roleFindUseCase.findAllById(requestDto.roles());
      user.setRoles(new HashSet<>(roles));
      for (var role : roles) {
        role.getUsers().add(user);
      }
    }

    User savedUser = userRepository.save(user);

    return savedUser;
  }

  @Override
  public User update(String id, UserCreateRequest requestDto) {

    User user = userFindUseCase.findById(id);

    for (var role : user.getRoles()) {
      role.getUsers().remove(user);
    }

    userMapper.update(user, requestDto);

    if (requestDto.password() != null) {
      user.setPassword(Password.create(passwordEncoder.encode(requestDto.password())));
    }

    if (requestDto.roles() != null) {
      List<Role> roles = roleFindUseCase.findAllById(requestDto.roles());
      user.setRoles(new HashSet<>(roles));
      for (var role : roles) {
        role.getUsers().add(user);
      }
    }

    User updatedUser = userRepository.save(user);

    return updatedUser;

  }

  @Override
  public User delete(String id) {

    User user = userFindUseCase.findById(id);

    Set<Role> roles = user.getRoles();

    user.softDelete();

    for (var role : roles) {
      role.getUsers().remove(user);
    }

    User deleteUser = userRepository.save(user);

    return deleteUser;
  }

}
