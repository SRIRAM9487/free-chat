package com.arch.micro_service.auth_server.role.infrastructure.controller.role;

import java.util.List;

import com.arch.micro_service.auth_server.role.application.service.role.RoleCrudService;
import com.arch.micro_service.auth_server.role.application.service.role.RoleService;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.mapper.RoleMapper;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RoleUserMetaDataResponse;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/role")
@RequiredArgsConstructor
public class RoleController {

  private final RoleService service;
  private final RoleCrudService crudService;
  private final RoleMapper roleMapper;

  @PatchMapping("/toggle/{id}")
  @PreAuthorize("hasAuthority('ROLE_TOGGLE')")
  public ResponseEntity<ApiResponse<String>> toggleStatus(@PathVariable("id") String id) {
    var role = service.toggleActive(id);
    var response = ApiResponse.create(role.getTitle() + (role.isActive() ? " Enabled" : " Disabled") + " Successfully");
    return ResponseEntity.ok(response);
  }

  @GetMapping("/user/meta")
  @PreAuthorize("hasAuthority('ROLE_META')")
  public ResponseEntity<ApiResponse<List<RoleUserMetaDataResponse>>> userMeta() {
    var roles = crudService.getAll();
    var dtos = roles.stream().map(roleMapper::toRoleUserMetaData).toList();
    var response = ApiResponse.create(dtos);
    return ResponseEntity.ok(response);
  }

}
