package com.arch.micro_service.auth_server.role.application.aop;

import com.arch.micro_service.auth_server.log.CustomLogger;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request.PermissionCreateRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
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

  

  @Pointcut("execution(* com.arch.micro_service.auth_server.role.application.service.permission.impl.PermissionCrudServiceImpl.create(..))")
  public void create() {
  }

  @Pointcut("execution(* com.arch.micro_service.auth_server.role.application.service.permission.impl.PermissionCrudServiceImpl.update(..))")
  public void update() {
  }

  @Pointcut("execution(* com.arch.micro_service.auth_server.role.application.service.permission.impl.PermissionCrudServiceImpl.delete(..))")
  public void delete() {
  }

  @After("create()")
  public void beforCreate(JoinPoint joinPoint) {

    var method = (MethodSignature) joinPoint.getSignature();
    String invocation = method.getClass() + method.getName();

    Object[] args = joinPoint.getArgs();
    PermissionCreateRequest request = (PermissionCreateRequest) args[0];

    // customLogger.success(invocation, message, data, "");
  }

}
