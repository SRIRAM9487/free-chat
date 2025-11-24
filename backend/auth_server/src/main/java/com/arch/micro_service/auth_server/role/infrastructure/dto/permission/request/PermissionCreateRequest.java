package com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request;

public record PermissionCreateRequest(
    String title,
    boolean active) {

  public static PermissionCreateRequest testPermission(String title) {
    return new PermissionCreateRequest(title, true);
  }
}
