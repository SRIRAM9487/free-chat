package com.arch.micro_service.chat_server.logger.event;

import com.arch.micro_service.chat_server.shared.domain.exception.BaseException;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class LogExceptionEvent extends ApplicationEvent {

  private final BaseException baseException;

  public LogExceptionEvent(Object source, BaseException baseException) {
    super(source);
    this.baseException = baseException;
  }

}
