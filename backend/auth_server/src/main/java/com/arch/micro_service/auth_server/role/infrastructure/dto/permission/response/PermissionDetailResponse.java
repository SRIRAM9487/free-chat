package com.arch.micro_service.auth_server.role.infrastructure.dto.permission.response;

import java.util.UUID;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.response.AbstractDetailResponse;

public record PermissionDetailResponse(
    UUID id,
    String title,
    boolean active) implements AbstractDetailResponse {
}
