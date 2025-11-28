package com.arch.micro_service.notification_server.email.infrastructure.client;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import com.arch.micro_service.notification_server.email.infrastructure.event.EmailVerificationEvent;
import com.arch.micro_service.notification_server.email.infrastructure.event.PasswordResetEvent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailClient {

  @Value("${spring.mail.username}")
  private String from;

  private final JavaMailSender javaMailSender;

  public void sendVerificationEmail(EmailVerificationEvent event) {
    String email = event.email();
    String userId = event.userId();
    String token = event.token();

    String verificationLink = "http://localhost:8500/v1/user/email/verify?token=" + token + "&email=" + email;

    ClassPathResource file = new ClassPathResource("templates/email/v1_email-verification.html");
    log.trace("Email html template loaded");

    String template;
    try {
      template = Files.readString(file.getFile().toPath(), StandardCharsets.UTF_8);
      String page = template.replace("{{VERIFICATION_LINK}}", verificationLink).replace("{{username}}", userId);
      log.trace("Email Html template updated");

      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
      messageHelper.setFrom(from);
      messageHelper.setTo(email);
      messageHelper.setSubject("Email Verification");
      messageHelper.setText(page, true);

      log.trace("Sending the email....");
      javaMailSender.send(message);
      log.trace("Email sent successfully");

    } catch (MessagingException e) {
      log.trace("Failed to send email : {}", e);
    } catch (IOException e) {
      log.trace("Failed to load the html template", e);
      e.printStackTrace();
    }
  }

  public void sendPasswordResetEmail(PasswordResetEvent event) {
    String email = event.email();
    String userId = event.userId();
    String token = event.token();

    String verificationLink = "http://localhost:8500/v1/user/login/new?token=" + token + "&email=" + email;

    ClassPathResource file = new ClassPathResource("templates/email/v1_password-reset.html");
    log.trace("Password Html template loaded");

    String template;
    try {
      template = Files.readString(file.getFile().toPath(), StandardCharsets.UTF_8);
      String page = template.replace("{{RESET_PASSWORD_LINK}}", verificationLink).replace("{{username}}", userId);
      log.trace("Password html template updated");
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
      messageHelper.setFrom(from);
      messageHelper.setTo(email);
      messageHelper.setSubject("Password reset request");
      messageHelper.setText(page, true);

      log.trace("Sending the email....");
      javaMailSender.send(message);
      log.trace("Password reset Email sent successfully");

    } catch (MessagingException e) {
      log.trace("Failed to send email : {}", e);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
