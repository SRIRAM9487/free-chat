package com.arch.micro_service.auth_server.role.infrastructure.controller.permission;

import java.util.List;

import com.arch.micro_service.auth_server.role.application.constant.PermissionConstant;
import com.arch.micro_service.auth_server.role.application.service.permission.PermissionCrudService;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.mapper.PermissionMapper;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request.PermissionCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.response.PermissionDetailResponse;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiResponse;

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
@RequestMapping("/v1/permission")
@RequiredArgsConstructor
public class PermissionCrudController {

  private final PermissionCrudService crudService;
  private final PermissionMapper permissionMapper;

  @GetMapping
  @PreAuthorize("hasAuthority('PERMISSION_READ')")
  public ResponseEntity<ApiResponse<List<PermissionDetailResponse>>> getAll() {
    var permissions = crudService.getAll().stream().map(permissionMapper::fromPermission).toList();
    var response = ApiResponse.create(permissions);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('PERMISSION_READ')")
  public ResponseEntity<ApiResponse<PermissionDetailResponse>> get(@PathVariable("id") String id) {
    var permission = permissionMapper.fromPermission(crudService.get(id));
    var response = ApiResponse.create(permission);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('PERMISSION_CREATE')")
  public ResponseEntity<ApiResponse<String>> create(@RequestBody PermissionCreateRequest request) {
    crudService.create(request);
    var response = ApiResponse.create(PermissionConstant.CREATE);
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/update/{id}")
  @PreAuthorize("hasAuthority('PERMISSION_UPDATE')")
  public ResponseEntity<ApiResponse<String>> update(@PathVariable("id") String id,
      @RequestBody PermissionCreateRequest request) {
    crudService.update(id, request);
    var response = ApiResponse.create(PermissionConstant.UPDATE);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('PERMISSION_DELETE')")
  public ResponseEntity<ApiResponse<String>> delete(@PathVariable("id") String id) {
    crudService.delete(id);
    var response = ApiResponse.create(PermissionConstant.DELETE);
    return ResponseEntity.ok(response);
  }

}
