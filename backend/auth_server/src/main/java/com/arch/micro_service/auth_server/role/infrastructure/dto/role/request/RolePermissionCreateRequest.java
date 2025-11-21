package com.arch.micro_service.auth_server.role.infrastructure.dto.role.request;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.request.AbstractCreateRequest;

public record RolePermissionCreateRequest(
    String permissionId,
    boolean active) implements AbstractCreateRequest {
}
