package com.arch.micro_service.auth_server.role.infrastructure.dto.permission.response;

public record PermissionDetailResponse(
    Long id,
    String title,
    boolean active) {
}
