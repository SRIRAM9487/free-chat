package com.arch.micro_service.auth_server.message.infrastructure.event;

import java.io.Serializable;

public record EmailVerificationEvent(String email, String token) implements Serializable {
}
