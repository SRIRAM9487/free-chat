package com.arch.micro_service.auth_server.role.infrastructure.dto.role.response;

public record RolePermissionDetailResponse(
    Long id,
    String permissionId,
    String title,
    boolean active,
    boolean activeStatus) {

}
