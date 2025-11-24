package com.arch.micro_service.auth_server.role.infrastructure.dto.role.response;

import java.util.List;
import java.util.UUID;

public record RoleDetailResponse(
    Long id,
    String title,
    boolean active,
    List<RolePermissionDetailResponse> rolePermissions) {
}
