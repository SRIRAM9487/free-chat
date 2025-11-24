package com.arch.micro_service.auth_server.user.infrastructure.dto.response;

import java.util.List;

public record UserLoginResponse(
    String userId,
    String userName,
    List<String> role,
    String token) {
}
