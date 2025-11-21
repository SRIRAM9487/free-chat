package com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.request.AbstractCreateRequest;

public record PermissionCreateRequest(
    String title,
    boolean active) implements AbstractCreateRequest {

  public static PermissionCreateRequest testPermission(String title) {
    return new PermissionCreateRequest(title, true);
  }
}
