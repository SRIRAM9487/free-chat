package com.arch.micro_service.auth_server.role.infrastructure.dto.role.mapper;

import java.util.List;

import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.domain.etntiy.RolePermission;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RoleCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RolePermissionCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RoleDetailResponse;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RolePermissionDetailResponse;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RoleUserMetaDataResponse;

import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

  private RolePermissionDetailResponse fromRolePermission(RolePermission rolePermissions) {
    RolePermissionDetailResponse response = new RolePermissionDetailResponse(
        rolePermissions.getId(),
        rolePermissions.getPermission().getId().toString(),
        rolePermissions.getPermission().getTitle(),
        rolePermissions.isActive(),
        rolePermissions.getPermission().isActive());
    return response;
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

  public void updateRole(Role role, RoleCreateRequest requestDto) {
    role.setTitle(requestDto.title());
    role.setActive(requestDto.active());
  }

  public RoleUserMetaDataResponse toRoleUserMetaData(Role role) {
    return new RoleUserMetaDataResponse(role.getId().toString(), role.getTitle());
  }
}
