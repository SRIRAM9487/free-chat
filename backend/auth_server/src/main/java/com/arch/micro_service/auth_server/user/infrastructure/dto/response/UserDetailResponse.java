package com.arch.micro_service.auth_server.user.infrastructure.dto.response;

import java.util.List;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.response.AbstractDetailResponse;

public record UserDetailResponse(
    String id,
    String name,
    String userName,
    String email,
    String emailVerified,
    String gender,
    List<String> roles,
    String isAccountLocked,
    String isAccountExpired,
    String isEnabled) implements AbstractDetailResponse {
}
