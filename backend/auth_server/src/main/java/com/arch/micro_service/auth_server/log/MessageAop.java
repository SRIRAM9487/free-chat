package com.arch.micro_service.auth_server.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class MessageAop {

  private final Logger log = LoggerFactory.getLogger("FileLogger");

  @Pointcut("execution(* com.arch.micro_service.auth_server.message.infrastructure.publisher.EmailEventPublisher.publishVerificationEmail(..))")
  private void sendEmail() {
  }

  @Before("sendEmail()")
  public void sendEmail(JoinPoint joinPoint) {
    log.trace("Email Verification published to message broker");
  }

}
