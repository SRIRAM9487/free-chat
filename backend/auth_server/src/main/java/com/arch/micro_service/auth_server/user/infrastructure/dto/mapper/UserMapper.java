package com.arch.micro_service.auth_server.user.infrastructure.dto.mapper;

import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserCreateRequest;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserDetailResponse;

public class UserMapper {

  public UserDetailResponse fromUser(User user) {
    return new UserDetailResponse();
  }

  public void update(User user, UserCreateRequest requestDto) {
  }

  public User toUser(UserCreateRequest requestDto) {
    return new User();
  }
}
