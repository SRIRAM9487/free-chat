package com.arch.micro_service.auth_server.role.infrastructure.dto.role.request;

import java.util.List;

public record RoleCreateRequest(
    String title,
    boolean active,
    List<RolePermissionCreateRequest> rolePermissions) {

}
