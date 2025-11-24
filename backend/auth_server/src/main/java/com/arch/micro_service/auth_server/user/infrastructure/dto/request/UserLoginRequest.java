package com.arch.micro_service.auth_server.user.infrastructure.dto.request;

public record UserLoginRequest(String userId, String password) {
}
