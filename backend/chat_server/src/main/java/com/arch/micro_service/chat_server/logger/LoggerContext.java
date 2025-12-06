package com.arch.micro_service.chat_server.logger;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Component
@RequestScope
@AllArgsConstructor
public class LoggerContext {
  private final String path;
  private final String method;
  private final long startMills;
  private final String ip;
  private final String userAgent;
}
