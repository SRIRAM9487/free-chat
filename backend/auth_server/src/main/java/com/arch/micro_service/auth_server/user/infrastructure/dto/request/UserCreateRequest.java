package com.arch.micro_service.auth_server.user.infrastructure.dto.request;

import java.util.List;
import java.util.UUID;

public record UserCreateRequest(
    String name,
    String userName,
    String password,
    String email,
    String gender,
    boolean accountNonExpired,
    boolean accountNonLocked,
    boolean enabled,
    List<Long> roles) {
}
