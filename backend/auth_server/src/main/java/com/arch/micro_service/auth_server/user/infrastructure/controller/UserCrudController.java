package com.arch.micro_service.auth_server.user.infrastructure.controller;

import java.util.List;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiResponse;
import com.arch.micro_service.auth_server.user.application.constant.UserCrudConstant;
import com.arch.micro_service.auth_server.user.application.service.UserCrudService;
import com.arch.micro_service.auth_server.user.infrastructure.dto.mapper.UserMapper;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserCreateRequest;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserDetailResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class UserCrudController {

  private final UserCrudService crudService;
  private final UserMapper userMapper;

  @GetMapping
  @PreAuthorize("hasAuthority('USER_VIEW')")
  public ResponseEntity<ApiResponse<List<UserDetailResponse>>> getAll() {
    var users = crudService.getAll().stream().map(userMapper::fromUser).toList();
    var response = ApiResponse.create(users);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('USER_VIEW')")
  public ResponseEntity<ApiResponse<UserDetailResponse>> get(@PathVariable("id") String id) {
    var user = crudService.get(id);
    var response = ApiResponse.create(userMapper.fromUser(user));
    return ResponseEntity.ok(response);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('USER_CREATE')")
  public ResponseEntity<ApiResponse<String>> create(@RequestBody UserCreateRequest request) {
    crudService.create(request);
    var response = ApiResponse.create(UserCrudConstant.CREATE);
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/update/{id}")
  @PreAuthorize("hasAuthority('USER_UPDATE')")
  public ResponseEntity<ApiResponse<String>> update(@PathVariable("id") String id,
      @RequestBody UserCreateRequest request) {
    crudService.update(id, request);
    var response = ApiResponse.create(UserCrudConstant.UPDATE);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('USER_DELETE')")
  public ResponseEntity<ApiResponse<String>> delete(@PathVariable("id") String id) {
    crudService.delete(id);
    var response = ApiResponse.create(UserCrudConstant.DELETE);
    return ResponseEntity.ok(response);
  }

}
