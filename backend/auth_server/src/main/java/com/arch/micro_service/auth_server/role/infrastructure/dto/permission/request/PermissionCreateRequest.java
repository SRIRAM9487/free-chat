package com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request;

public record PermissionCreateRequest(
    String title,
    boolean active) {

}
