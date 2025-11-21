package com.arch.micro_service.auth_server.role.infrastructure.dto.permission.mapper;

import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request.PermissionCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.response.PermissionDetailResponse;

import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {

  public PermissionDetailResponse fromPermission(Permission permission) {
    PermissionDetailResponse response = new PermissionDetailResponse(
        permission.getId(),
        permission.getTitle(),
        permission.isActive());
    return response;
  }

  public void update(Permission permission, PermissionCreateRequest requestDto) {
    permission.setTitle(requestDto.title());
    permission.setActive(requestDto.active());
  }

  public Permission toPermission(PermissionCreateRequest requestDto) {
    Permission permission = Permission
        .builder()
        .title(requestDto.title())
        .active(requestDto.active())
        .build();
    return permission;
  }
}
