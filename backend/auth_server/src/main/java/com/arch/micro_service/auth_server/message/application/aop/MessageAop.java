package com.arch.micro_service.auth_server.message.application.aop;

import com.arch.micro_service.auth_server.log.CustomLogger;
import com.arch.micro_service.auth_server.message.infrastructure.event.EmailVerificationEvent;
import com.arch.micro_service.auth_server.message.infrastructure.event.PasswordResetEvent;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class MessageAop {

  private final CustomLogger log;

  @Pointcut("execution(* com.arch.micro_service.auth_server.message.infrastructure.publisher.EmailEventPublisher.publishVerificationEmail(..))")
  public void emailVerification() {
  }

  @Before("emailVerification()")
  public void sendEmailVerification(JoinPoint joinPoint) {
    var args = joinPoint.getArgs();
    EmailVerificationEvent event = (EmailVerificationEvent) args[0];
    String invocation = joinPoint.getSignature().getClass() + joinPoint.getSignature().getName();
    log.success(invocation, "Email Verification published " + event.email(), event, "");
  }

  @Pointcut("execution(* com.arch.micro_service.auth_server.message.infrastructure.publisher.EmailEventPublisher.publishPasswordResetEmail(..))")
  public void passwordReset() {
  }

  @Before("passwordReset()")
  public void sendPasswordReset(JoinPoint joinPoint) {
    var args = joinPoint.getArgs();
    PasswordResetEvent event = (PasswordResetEvent) args[0];
    String invocation = joinPoint.getSignature().getClass() + joinPoint.getSignature().getName();
    log.success(invocation, "Password reset request published " + event.email(), event, "");
  }
}
