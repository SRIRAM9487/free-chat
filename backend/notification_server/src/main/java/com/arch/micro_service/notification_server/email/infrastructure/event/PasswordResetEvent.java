package com.arch.micro_service.notification_server.email.infrastructure.event;

import java.io.Serializable;

public record PasswordResetEvent(String email, String userId, String token) implements Serializable {
}
