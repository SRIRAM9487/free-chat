package com.arch.micro_service.auth_server.role.infrastructure.dto.role.response;

import java.util.List;
import java.util.UUID;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.response.AbstractDetailResponse;

public record RoleDetailResponse(
    UUID id,
    String title,
    boolean active,
    List<RolePermissionDetailResponse> rolePermissions) implements AbstractDetailResponse {
}
