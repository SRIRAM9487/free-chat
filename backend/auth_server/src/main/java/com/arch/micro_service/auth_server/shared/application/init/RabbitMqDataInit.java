package com.arch.micro_service.auth_server.shared.application.init;

import com.arch.micro_service.auth_server.message.infrastructure.event.EmailVerificationEvent;
import com.arch.micro_service.auth_server.message.infrastructure.publisher.EmailEventPublisher;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RabbitMqDataInit implements CommandLineRunner {

  private final EmailEventPublisher emailEventPublisher;

  @Override
  public void run(String... args) throws Exception {

    emailEventPublisher.publish(new EmailVerificationEvent("test@gmail.com",
        "THIS IS AN TOKEN"));

  }

}
