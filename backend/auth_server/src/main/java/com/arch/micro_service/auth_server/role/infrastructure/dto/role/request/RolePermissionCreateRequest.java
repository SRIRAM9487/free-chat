package com.arch.micro_service.auth_server.role.infrastructure.dto.role.request;

public record RolePermissionCreateRequest(
    String permissionId,
    boolean active) {
}
