package com.arch.micro_service.auth_server.message.domain.event;

import java.io.Serializable;

public record EmailVerificationRequestEvent(String userEmail, String token) implements Serializable {
}
