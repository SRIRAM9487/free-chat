package com.arch.micro_service.auth_server.user.infrastructure.dto.request;

import java.util.List;
import java.util.UUID;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.request.AbstractCreateRequest;

public record UserCreateRequest(
    String name,
    String userName,
    String password,
    String email,
    String gender,
    List<UUID> roles) implements AbstractCreateRequest {
}
