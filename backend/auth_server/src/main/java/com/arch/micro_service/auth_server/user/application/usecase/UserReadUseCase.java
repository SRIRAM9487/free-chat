package com.arch.micro_service.auth_server.user.application.usecase;

import java.util.List;

import com.arch.micro_service.auth_server.user.application.service.UserService;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.infrastructure.dto.mapper.UserMapper;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserDetailResponse;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserReadUseCase {

  private final UserService userService;
  private final UserMapper userMapper;

  public List<UserDetailResponse> getAll() {

    List<User> users = userService.findAll();
    return users
        .stream()
        .map(userMapper::fromUser)
        .toList();
  }

  public UserDetailResponse get(String id) {
    User user = userService.findByUserId(id);
    return userMapper.fromUser(user);
  }

}
