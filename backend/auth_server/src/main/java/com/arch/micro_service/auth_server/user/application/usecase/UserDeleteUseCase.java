package com.arch.micro_service.auth_server.user.application.usecase;

import java.util.Set;

import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.user.application.constant.UserCrudConstant;
import com.arch.micro_service.auth_server.user.application.service.UserService;
import com.arch.micro_service.auth_server.user.domain.entity.User;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDeleteUseCase {

  private final UserService userService;

  public String delete(String id) {

    User user = userService.findById(id);

    Set<Role> roles = user.getRoles();

    for (var role : roles) {
      role.getUsers().remove(user);
    }

    userService.save(user);

    return UserCrudConstant.DELETE;
  }
}
