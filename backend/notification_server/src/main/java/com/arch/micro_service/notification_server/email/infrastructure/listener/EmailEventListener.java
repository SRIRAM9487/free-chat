package com.arch.micro_service.notification_server.email.infrastructure.listener;

import com.arch.micro_service.notification_server.email.infrastructure.client.EmailClient;
import com.arch.micro_service.notification_server.email.infrastructure.event.EmailVerificationEvent;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailEventListener {

  private final EmailClient emailClient;

  @RabbitListener(queues = "auth.email.verification.queue")
  public void sendEmail(EmailVerificationEvent event) {
    log.trace("Email verification Requested for {}", event.userId());
    emailClient.sendVerificationEmail(event);
  }

}
