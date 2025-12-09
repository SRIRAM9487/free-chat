package com.arch.micro_service.chat_server.logger.listener;

import java.time.LocalDateTime;

import com.arch.micro_service.chat_server.logger.context.MetaContext;
import com.arch.micro_service.chat_server.logger.context.MetaContextHolder;
import com.arch.micro_service.chat_server.logger.dto.AuditLogException;
import com.arch.micro_service.chat_server.logger.event.LogExceptionEvent;
import com.arch.micro_service.chat_server.logger.utils.LogUtils;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseException;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LogExceptionListener {

  private final LogUtils logUtils;

  public void logException(LogExceptionEvent event) {
    final MetaContext context = MetaContextHolder.get();
    final BaseException ex = event.getBaseException();

    var log = new AuditLogException(
        false,
        LocalDateTime.now(),
        context.getPath(),
        context.getMethod(),
        context.getUserId(),
        context.getIp(),
        context.getUserAgent(),
        context.getStartMills() - System.currentTimeMillis(),
        logUtils.service,
        ex.getHttpStatus().name(),
        ex.getCode(),
        ex.getMessage());
    System.out.println(log);
  }

}
