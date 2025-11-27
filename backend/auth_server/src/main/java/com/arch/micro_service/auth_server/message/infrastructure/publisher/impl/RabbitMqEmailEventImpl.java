package com.arch.micro_service.auth_server.message.infrastructure.publisher.impl;

import com.arch.micro_service.auth_server.message.infrastructure.event.EmailVerificationEvent;
import com.arch.micro_service.auth_server.message.infrastructure.publisher.EmailEventPublisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RabbitMqEmailEventImpl implements EmailEventPublisher {

  private final RabbitTemplate rabbitTemplate;

  // TODO:
  @Override
  public void publish(EmailVerificationEvent event) {
    rabbitTemplate.convertAndSend("auth.exchange", "auth.email.verification", event);
  }

}
