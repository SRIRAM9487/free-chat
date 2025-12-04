package com.arch.micro_service.auth_server.role.infrastructure.controller.role;

import java.util.List;

import com.arch.micro_service.auth_server.role.application.constant.RoleConstant;
import com.arch.micro_service.auth_server.role.application.service.role.RoleCrudService;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.mapper.RoleMapper;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RoleCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RoleDetailResponse;
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
@RequestMapping("/v1/role")
@RequiredArgsConstructor
public class RoleCrudController {

  private final RoleCrudService crudService;
  private final RoleMapper roleMapper;

  @GetMapping
  @PreAuthorize("hasAuthority('ROLE_READ')")
  public ResponseEntity<ApiResponse<List<RoleDetailResponse>>> getAll() {
    var roles = crudService.getAll().stream().map(roleMapper::fromRole).toList();
    var response = ApiResponse.create(roles);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('ROLE_READ')")
  public ResponseEntity<ApiResponse<RoleDetailResponse>> get(@PathVariable("id") String id) {
    var role = crudService.get(id);
    var response = ApiResponse.create(roleMapper.fromRole(role));
    return ResponseEntity.ok(response);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('ROLE_CREATE')")
  public ResponseEntity<ApiResponse<String>> create(@RequestBody RoleCreateRequest request) {
    crudService.create(request);
    var response = ApiResponse.create(RoleConstant.CREATE);
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/update/{id}")
  @PreAuthorize("hasAuthority('ROLE_UPDATE')")
  public ResponseEntity<ApiResponse<String>> update(
      @PathVariable("id") String id,
      @RequestBody RoleCreateRequest request) {
    crudService.update(id, request);
    var response = ApiResponse.create(RoleConstant.UPDATE);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ROLE_DELETE')")
  public ResponseEntity<ApiResponse<String>> delete(@PathVariable("id") String id) {
    crudService.delete(id);
    var response = ApiResponse.create(RoleConstant.DELETE);
    return ResponseEntity.ok(response);
  }
}
