package com.arch.micro_service.auth_server.role.infrastructure.dto.role.response;

import java.util.UUID;

public record RolePermissionDetailResponse(
    UUID id,
    String permissionId,
    String title,
    boolean active,
    boolean activeStatus) {

}
