package com.arch.micro_service.auth_server.user.application.usecase;

import java.util.HashSet;
import java.util.List;

import com.arch.micro_service.auth_server.role.application.service.RoleService;
import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.user.application.constant.UserCrudConstant;
import com.arch.micro_service.auth_server.user.application.service.UserService;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.vo.Password;
import com.arch.micro_service.auth_server.user.infrastructure.dto.mapper.UserMapper;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserCreateRequest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserUpdateUseCase {

  private final UserService userService;
  private final UserMapper userMapper;
  private final RoleService roleService;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public String update(String id, UserCreateRequest createRequest) {

    User user = userService.findById(id);

    for (var role : user.getRoles()) {
      role.getUsers().remove(user);
    }

    userMapper.update(user, createRequest);

    if (createRequest.password() != null) {
      user.setPassword(Password.create(passwordEncoder.encode(createRequest.password())));
    }

    if (createRequest.roles() != null) {
      List<Role> roles = roleService.findAllById(createRequest.roles());
      user.setRoles(new HashSet<>(roles));
      for (var role : roles) {
        role.getUsers().add(user);
      }
    }

    userService.save(user);

    return UserCrudConstant.UPDATE;
  }

}
