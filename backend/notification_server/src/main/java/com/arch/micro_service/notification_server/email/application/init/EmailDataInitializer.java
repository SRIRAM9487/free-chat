package com.arch.micro_service.notification_server.email.application.init;

import com.arch.micro_service.notification_server.email.infrastructure.client.EmailClient;
import com.arch.micro_service.notification_server.email.infrastructure.event.EmailVerificationEvent;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor
public class EmailDataInitializer implements CommandLineRunner {

  private final EmailClient emailClient;

  @Override
  public void run(String... args) throws Exception {

    emailClient.sendVerificationEmail(
        new EmailVerificationEvent("sriram.a.2023.cse@ritchennai.edu.in", "SRIRAM", "LKSFDJLKJFDSLJFLDSKLKFJSL"));

  }

}
