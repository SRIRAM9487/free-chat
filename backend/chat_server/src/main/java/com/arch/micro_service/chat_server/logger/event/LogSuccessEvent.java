package com.arch.micro_service.chat_server.logger.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class LogSuccessEvent extends ApplicationEvent {

  private final String message;
  private final Object before;
  private final Object after;

  public LogSuccessEvent(String message, Object before, Object after, Object source) {
    super(source);
    this.message = message;
    this.before = before;
    this.after = after;
  }

}
