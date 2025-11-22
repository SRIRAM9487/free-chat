package com.arch.micro_service.auth_server.user.infrastructure.controller;

import java.util.List;

import com.arch.micro_service.auth_server.shared.infrastructure.controller.AbstractCrudController;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiResponse;
import com.arch.micro_service.auth_server.user.application.usecase.UserCreateUseCase;
import com.arch.micro_service.auth_server.user.application.usecase.UserDeleteUseCase;
import com.arch.micro_service.auth_server.user.application.usecase.UserReadUseCase;
import com.arch.micro_service.auth_server.user.application.usecase.UserUpdateUseCase;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserCreateRequest;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserDetailResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserCrudController implements AbstractCrudController<UserCreateRequest, UserDetailResponse> {

  private final UserCreateUseCase createUseCase;
  private final UserUpdateUseCase updateUseCase;
  private final UserDeleteUseCase deleteUseCase;
  private final UserReadUseCase readUseCase;

  @Override
  @GetMapping
  public ResponseEntity<ApiResponse<List<UserDetailResponse>>> getAll() {
    var response = ApiResponse.create(readUseCase.getAll());
    return ResponseEntity.ok(response);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<UserDetailResponse>> get(@PathVariable("id") String id) {
    var response = ApiResponse.create(readUseCase.get(id));
    return ResponseEntity.ok(response);
  }

  @Override
  @PostMapping("/create")
  public ResponseEntity<ApiResponse<String>> create(@RequestBody UserCreateRequest request) {
    var response = ApiResponse.create(createUseCase.create(request));
    return ResponseEntity.ok(response);
  }

  @Override
  @PatchMapping("/update/{id}")
  public ResponseEntity<ApiResponse<String>> update(@PathVariable("id") String id,
      @RequestBody UserCreateRequest request) {
    var response = ApiResponse.create(updateUseCase.update(id, request));
    return ResponseEntity.ok(response);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<String>> delete(@PathVariable("id") String id) {
    var response = ApiResponse.create(deleteUseCase.delete(id));
    return ResponseEntity.ok(response);
  }

}
