package com.arch.micro_service.chat_server.logger.listener;

import java.time.LocalDateTime;

import com.arch.micro_service.chat_server.logger.context.MetaContext;
import com.arch.micro_service.chat_server.logger.context.MetaContextHolder;
import com.arch.micro_service.chat_server.logger.dto.AuditLogSuccess;
import com.arch.micro_service.chat_server.logger.event.LogSuccessEvent;
import com.arch.micro_service.chat_server.logger.utils.LogUtils;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LogSuccessListener {

  private final LogUtils logUtils;

  public void logSuccess(LogSuccessEvent event) {
    final MetaContext context = MetaContextHolder.get();
    var log = new AuditLogSuccess(
        true,
        LocalDateTime.now(),
        context.getPath(),
        context.getMethod(),
        context.getUserId(),
        context.getIp(),
        context.getUserAgent(),
        context.getStartMills() - System.currentTimeMillis(),
        logUtils.service,
        event.getMessage(),
        event.getBefore(),
        event.getAfter());
    System.out.println(log.toString());

  }

}
