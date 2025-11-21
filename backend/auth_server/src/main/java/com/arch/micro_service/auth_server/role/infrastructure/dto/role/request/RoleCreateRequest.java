package com.arch.micro_service.auth_server.role.infrastructure.dto.role.request;

import java.util.List;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.request.AbstractCreateRequest;

public record RoleCreateRequest(
    String title,
    boolean active,
    List<RolePermissionCreateRequest> rolePermissions) implements AbstractCreateRequest {

}
