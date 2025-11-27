package com.arch.micro_service.notification_server.email.infrastructure.client;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import com.arch.micro_service.notification_server.email.infrastructure.event.EmailVerificationEvent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailClient {

  @Value("{spring.mail.username}")
  private String from;

  private final JavaMailSender javaMailSender;

  public void sendVerificationEmail(EmailVerificationEvent event) {
    String email = event.email();
    String userId = event.userId();
    String token = event.token();

    String verificationLink = "http://localhost:8500/v1/user/email/verify?token=" + token + "&email=" + email;

    ClassPathResource file = new ClassPathResource("templates/email/v1_email-verification.html");
    log.trace("html template loaded");

    try {

      String template = Files.readString(file.getFile().toPath(), StandardCharsets.UTF_8);
      String page = template.replace("{{VERIFICATION_LINK}}", verificationLink).replace("{{username}}", userId);
      log.trace("Html template updated");

      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

      messageHelper.setFrom(from);
      messageHelper.setTo(email);
      messageHelper.setSubject("Email verification");
      messageHelper.setText(page, true);

      javaMailSender.send(message);
      log.trace("Verification email sent successfully");

    } catch (Exception e) {
      log.info("Failed to send Email Verification {}", e.getLocalizedMessage());
    }
  }

}
