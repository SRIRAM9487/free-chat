package com.arch.micro_service.auth_server.shared.application.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodInvocationAspect {

  private static final Logger log = LoggerFactory.getLogger("MethodLogger");

  @Pointcut("execution(* com.arch.micro_service.auth_server..domain..*(..))")
  private void domainMethods() {
  }

  @Pointcut("execution(* com.arch.micro_service.auth_server..application..*(..))")
  private void applicationMethods() {
  }

  @Pointcut("execution(* com.arch_micro_service.auth_server..infrastructure..*(..))")
  private void infrastructureMethods() {
  }

  @Pointcut("execution(* com.arch.micro_service.auth_server..security..handler..*(..))")
  private void handlerMethods() {
  }

  @Pointcut("domainMethods() || applicationMethods() || infrastructureMethods() || handlerMethods()")
  private void monitoredMethods() {
  }

  @Before("monitoredMethods()")
  public void logBefore(JoinPoint joinPoint) {
    if (!log.isDebugEnabled())
      return;

    log.debug("\n[Before] {}.{}({})",
        joinPoint.getTarget().getClass().getSimpleName(),
        joinPoint.getSignature().getName(),
        buildArgString(joinPoint));
  }

  @AfterReturning(pointcut = "monitoredMethods()", returning = "result")
  public void logAfter(JoinPoint joinPoint, Object result) {

    if (!log.isDebugEnabled())
      return;

    log.debug("\n[After] {}.{} -> {}",
        joinPoint.getTarget().getClass().getSimpleName(),
        joinPoint.getSignature().getName(),
        safeToString(result));
  }

  private String buildArgString(JoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();
    if (args == null || args.length == 0) {
      return "";
    }

    CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
    String[] paramNames = codeSignature.getParameterNames();

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < args.length; i++) {
      sb.append(paramNames[i])
          .append("=")
          .append(safeToString(args[i]));
      if (i < args.length - 1)
        sb.append(", ");
    }

    return sb.toString();
  }

  private String safeToString(Object value) {
    if (value == null)
      return "null";

    if (isPrimitiveOrWrapper(value.getClass()) || value instanceof String) {
      return value.toString();
    }

    return value.getClass().getSimpleName() + "@" + Integer.toHexString(value.hashCode());
  }

  private boolean isPrimitiveOrWrapper(Class<?> cls) {
    return cls.isPrimitive() ||
        cls == Integer.class ||
        cls == Long.class ||
        cls == Boolean.class ||
        cls == Double.class ||
        cls == Float.class ||
        cls == Short.class ||
        cls == Byte.class ||
        cls == Character.class;
  }
}
