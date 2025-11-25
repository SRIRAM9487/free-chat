package com.arch.micro_service.auth_server.user.application.service;

import java.util.List;

import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserCreateRequest;

public interface UserCrudService {

  List<User> getAll();

  User get(String id);

  User create(UserCreateRequest requestDto);

  User update(String id, UserCreateRequest requestDto);

  User delete(String id);

}
