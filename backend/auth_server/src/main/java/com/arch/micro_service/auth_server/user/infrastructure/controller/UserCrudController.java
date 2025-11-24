package com.arch.micro_service.auth_server.user.infrastructure.controller;

import java.util.List;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiResponse;
import com.arch.micro_service.auth_server.user.application.service.UserCrudService;
import com.arch.micro_service.auth_server.user.infrastructure.dto.mapper.UserMapper;
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
public class UserCrudController {

  private final UserCrudService crudService;
  private final UserMapper userMapper;

  @GetMapping
  public ResponseEntity<ApiResponse<List<UserDetailResponse>>> getAll() {
    var users = crudService.getAll().stream().map(userMapper::fromUser).toList();
    var response = ApiResponse.create(users);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<UserDetailResponse>> get(@PathVariable("id") String id) {
    var user = crudService.get(id);
    var response = ApiResponse.create(userMapper.fromUser(user));
    return ResponseEntity.ok(response);
  }

  @PostMapping("/create")
  public ResponseEntity<ApiResponse<String>> create(@RequestBody UserCreateRequest request) {
    var response = ApiResponse.create(crudService.create(request));
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/update/{id}")
  public ResponseEntity<ApiResponse<String>> update(@PathVariable("id") String id,
      @RequestBody UserCreateRequest request) {
    var response = ApiResponse.create(crudService.update(id, request));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<String>> delete(@PathVariable("id") String id) {
    var response = ApiResponse.create(crudService.delete(id));
    return ResponseEntity.ok(response);
  }

}
