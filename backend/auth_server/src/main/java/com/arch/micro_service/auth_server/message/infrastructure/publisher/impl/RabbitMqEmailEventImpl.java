package com.arch.micro_service.auth_server.message.infrastructure.publisher.impl;

import com.arch.micro_service.auth_server.message.infrastructure.event.EmailVerificationEvent;
import com.arch.micro_service.auth_server.message.infrastructure.publisher.EmailEventPublisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMqEmailEventImpl implements EmailEventPublisher {

  private final RabbitTemplate rabbitTemplate;

  @Override
  public void publish(EmailVerificationEvent event) {
    rabbitTemplate.convertAndSend("auth.exchange", "auth.email.verification", event);
    log.trace("Email Verification published to message broker");
  }

}
