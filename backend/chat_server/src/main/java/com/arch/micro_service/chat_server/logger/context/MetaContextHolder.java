package com.arch.micro_service.chat_server.logger.context;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class MetaContextHolder {

  private static final ThreadLocal<MetaContext> CTX = new ThreadLocal<>();

  public static void set(MetaContext context) {
    CTX.set(context);
  }

  public static MetaContext get() {
    return CTX.get();
  }

  public static void clear() {
    CTX.remove();
  }
}
