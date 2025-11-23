package com.arch.micro_service.auth_server.user.application.service;

import java.util.List;

import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserCreateRequest;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserDetailResponse;

public interface UserCrudService {

  List<UserDetailResponse> getAll();

  UserDetailResponse get(String id);

  String create(UserCreateRequest requestDto);

  String update(String id, UserCreateRequest requestDto);

  String delete(String id);

}
