package com.arch.micro_service.auth_server.message.infrastructure.event;

import java.io.Serializable;

public record PasswordResetEvent(String email, String userId, String token) implements Serializable {
}
