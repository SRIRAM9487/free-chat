package com.arch.micro_service.auth_server.message.infrastructure.controller;

import com.arch.micro_service.auth_server.message.infrastructure.event.EmailVerificationEvent;
import com.arch.micro_service.auth_server.message.infrastructure.publisher.EmailEventPublisher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rb/test")
@RequiredArgsConstructor
public class RabbitTestController {

  private final EmailEventPublisher emailEventPublisher;

  @GetMapping("/email")
  public String sendEmail() {
    emailEventPublisher
        .publish(
            new EmailVerificationEvent("sriram.a.2023.cse@ritchennai.edu.in", "SRIRAM", "Thisisntestt123123oken"));
    return "EMAIL SENT SUCCESSFULLY";
  }

}
