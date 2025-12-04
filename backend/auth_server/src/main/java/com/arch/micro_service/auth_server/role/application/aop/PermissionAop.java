package com.arch.micro_service.auth_server.role.application.aop;

import com.arch.micro_service.auth_server.log.CustomLogger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class PermissionAop {

  private final CustomLogger customLogger;

  @Pointcut("execution(public  String com.arch.micro_service.auth_server.user.infrastructure.controller.UserCrudController.delete(..))")
  public void test() {
  }

  @Pointcut("execution(* com.arch.micro_service.auth_server.role.application.service.permission.impl.PermissionCrudServiceImpl.create(..))")
  public void create() {
  }

  @Pointcut("execution(* com.arch.micro_service.auth_server.role.application.service.permission.impl.PermissionCrudServiceImpl.update(..))")
  public void update() {
  }

  @Pointcut("execution(* com.arch.micro_service.auth_server.role.application.service.permission.impl.PermissionCrudServiceImpl.delete(..))")
  public void delete() {
  }

}
