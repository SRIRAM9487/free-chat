package com.arch.micro_service.auth_server.log;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LoggerContextHolder {

  private static final ThreadLocal<LoggerContext> CTX = new ThreadLocal<>();

  public static void set(LoggerContext context) {
    CTX.set(context);
  }

  public static LoggerContext get() {
    return CTX.get();
  }

  public static void clear() {
    CTX.remove();
  }
}
