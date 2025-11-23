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
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserDetailResponse;
import com.arch.micro_service.auth_server.user.infrastructure.persistence.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCrudServiceImpl implements UserCrudService {

  private final UserRepository userRepository;
  private final UserFindUseCase userFindUseCase;
  private final RoleFindUseCase roleFindUseCase;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public List<UserDetailResponse> getAll() {
    var users = userRepository
        .findAll()
        .stream()
        .filter(p -> p.getDeletedAt() != null)
        .map(userMapper::fromUser)
        .toList();

    return users;
  }

  @Override
  public UserDetailResponse get(String id) {
    return userMapper.fromUser(userFindUseCase.findById(id));
  }

  @Override
  public String create(UserCreateRequest requestDto) {

    User user = userMapper.toUser(requestDto);
    user.updatePassword(passwordEncoder.encode(requestDto.password()));

    if (requestDto.roles() != null) {
      List<Role> roles = roleFindUseCase.findAllById(requestDto.roles());
      user.setRoles(new HashSet<>(roles));
      for (var role : roles) {
        role.getUsers().add(user);
      }
    }

    userRepository.save(user);

    return UserCrudConstant.CREATE;
  }

  @Override
  public String update(String id, UserCreateRequest requestDto) {

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

    userRepository.save(user);

    return UserCrudConstant.UPDATE;

  }

  @Override
  public String delete(String id) {

    User user = userFindUseCase.findById(id);

    Set<Role> roles = user.getRoles();

    for (var role : roles) {
      role.getUsers().remove(user);
    }

    userRepository.save(user);

    return UserCrudConstant.DELETE;
  }

}
