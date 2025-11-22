package com.arch.micro_service.auth_server.user.infrastructure.dto.response;

import java.util.List;
import java.util.UUID;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.response.AbstractDetailResponse;

public record UserDetailResponse(
    UUID id,
    String name,
    String userName,
    String email,
    boolean emailVerified,
    String gender,
    boolean accountNonExpired,
    boolean accountNonLocked,
    boolean enabled,
    List<String> roles) implements AbstractDetailResponse {
}
