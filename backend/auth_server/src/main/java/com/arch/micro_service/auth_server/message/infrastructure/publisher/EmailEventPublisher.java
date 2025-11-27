package com.arch.micro_service.auth_server.message.infrastructure.publisher;

import com.arch.micro_service.auth_server.message.infrastructure.event.EmailVerificationEvent;

public interface EmailEventPublisher {

  void publish(EmailVerificationEvent event);

}
