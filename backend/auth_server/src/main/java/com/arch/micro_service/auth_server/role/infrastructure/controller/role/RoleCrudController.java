package com.arch.micro_service.auth_server.role.infrastructure.controller.role;

import java.util.List;

import com.arch.micro_service.auth_server.role.application.service.role.RoleCrudService;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.mapper.RoleMapper;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RoleCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RoleDetailResponse;
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
@RequestMapping("/v1/role")
@RequiredArgsConstructor
public class RoleCrudController {

  private final RoleCrudService crudService;
  private final RoleMapper roleMapper;

  @GetMapping
  public ResponseEntity<ApiResponse<List<RoleDetailResponse>>> getAll() {
    var roles = crudService.getAll().stream().map(roleMapper::fromRole).toList();
    var response = ApiResponse.create(roles);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<RoleDetailResponse>> get(@PathVariable("id") String id) {
    var role = crudService.get(id);
    var response = ApiResponse.create(roleMapper.fromRole(role));
    return ResponseEntity.ok(response);
  }

  @PostMapping("/create")
  public ResponseEntity<ApiResponse<String>> create(@RequestBody RoleCreateRequest request) {
    var response = ApiResponse.create(crudService.create(request));
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/update/{id}")
  public ResponseEntity<ApiResponse<String>> update(
      @PathVariable("id") String id,
      @RequestBody RoleCreateRequest request) {

    var response = ApiResponse.create(crudService.update(id, request));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<String>> delete(@PathVariable("id") String id) {
    var response = ApiResponse.create(crudService.delete(id));
    return ResponseEntity.ok(response);
  }
}
