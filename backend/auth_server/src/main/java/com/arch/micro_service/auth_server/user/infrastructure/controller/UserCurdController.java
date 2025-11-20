package com.arch.micro_service.auth_server.user.infrastructure.controller;

import java.util.List;

import com.arch.micro_service.auth_server.shared.infrastructure.controller.AbstractCrudController;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiResponse;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.response.AbstractDetailResponse;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserCreateRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserCurdController implements AbstractCrudController<UserCreateRequest> {

  @Override
  public ApiResponse<List<AbstractDetailResponse>> getAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAll'");
  }

  @Override
  public ApiResponse<AbstractDetailResponse> get(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'get'");
  }

  @Override
  public ApiResponse<String> create(UserCreateRequest request) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  public ApiResponse<String> update(String id, UserCreateRequest request) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public ApiResponse<String> create(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

}
