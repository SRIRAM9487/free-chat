package com.arch.micro_service.auth_server.role.infrastructure.dto.role.mapper;

import java.util.List;

import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.domain.etntiy.RolePermission;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RoleCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RolePermissionCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RoleDetailResponse;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RolePermissionDetailResponse;

import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

  private RolePermissionDetailResponse fromRolePermission(RolePermission permission) {
    return new RolePermissionDetailResponse(
        permission.getId(),
        permission.getPermission().getTitle(),
        permission.isActive(),
        permission.isActiveStatus());
  }

  public RolePermission toRolePermission(RolePermissionCreateRequest requestDto) {
    RolePermission rolePermission = RolePermission.builder()
        .active(requestDto.active())
        .build();

    return rolePermission;
  }

  public RoleDetailResponse fromRole(Role role) {
    List<RolePermissionDetailResponse> rolePermissions = role
        .getRolePermissions()
        .stream()
        .map(this::fromRolePermission)
        .toList();

    return new RoleDetailResponse(
        role.getId(),
        role.getTitle(),
        role.isActive(),
        rolePermissions);
  }

  public Role toRole(RoleCreateRequest requestDto) {

    Role role = Role.builder()
        .title(requestDto.title())
        .active(requestDto.active())
        .build();

    return role;
  }

  public void updateRolePermission(RolePermission rolePermission, RolePermissionCreateRequest createRequest) {
    rolePermission.setActive(createRequest.active());
  }

  public void updateRole(Role role, RoleCreateRequest requestDto) {
    role.setTitle(requestDto.title());
    role.setActive(requestDto.active());
  }
}
