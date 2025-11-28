package com.arch.micro_service.auth_server.message.infrastructure.publisher;

import com.arch.micro_service.auth_server.message.infrastructure.event.EmailVerificationEvent;
import com.arch.micro_service.auth_server.message.infrastructure.event.PasswordResetEvent;

public interface EmailEventPublisher {

  void publishVerificationEmail(EmailVerificationEvent event);

  void publishPasswordResetEmail(PasswordResetEvent event);

}
