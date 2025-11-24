package com.arch.micro_service.auth_server.role.infrastructure.dto.permission.response;

import java.util.UUID;

public record PermissionDetailResponse(
    UUID id,
    String title,
    boolean active) {
}
