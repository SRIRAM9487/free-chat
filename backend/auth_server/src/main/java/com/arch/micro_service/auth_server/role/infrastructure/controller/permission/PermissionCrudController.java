package com.arch.micro_service.auth_server.role.infrastructure.controller.permission;

import java.util.List;

import com.arch.micro_service.auth_server.role.application.service.permission.PermissionCrudService;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request.PermissionCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.response.PermissionDetailResponse;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiResponse;

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
@RequestMapping("/v1/permission")
@RequiredArgsConstructor
public class PermissionCrudController {

  private final PermissionCrudService crudService;

  @GetMapping
  public ResponseEntity<ApiResponse<List<PermissionDetailResponse>>> getAll() {
    var response = ApiResponse.create(crudService.getAll());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<PermissionDetailResponse>> get(@PathVariable("id") String id) {
    var response = ApiResponse.create(crudService.get(id));
    return ResponseEntity.ok(response);
  }

  @PostMapping("/create")
  public ResponseEntity<ApiResponse<String>> create(@RequestBody PermissionCreateRequest request) {
    var response = ApiResponse.create(crudService.create(request));
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/update/{id}")
  public ResponseEntity<ApiResponse<String>> update(@PathVariable("id") String id,
      @RequestBody PermissionCreateRequest request) {
    var response = ApiResponse.create(crudService.update(id, request));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<String>> delete(@PathVariable("id") String id) {
    var response = ApiResponse.create(crudService.delete(id));
    return ResponseEntity.ok(response);
  }

}
