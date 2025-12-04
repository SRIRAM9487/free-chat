package com.arch.micro_service.auth_server.log;

import java.time.Instant;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseException;
import com.arch.micro_service.auth_server.user.domain.entity.UserImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CustomLogger {

  private final Logger log = LoggerFactory.getLogger("FileLogger");
  private static final ObjectMapper mapper = new ObjectMapper();
  private final String serviceName = "Auth Service";

  private String toJson(Object event) {
    if (event == null) {
      return "{\"error\":\"Failed to serialize log event\"}";
    }
    try {
      return mapper.writeValueAsString(event);
    } catch (JsonProcessingException e) {
      return "{\"error\":\"Failed to serialize log event\"}";
    }
  }

  private String currentUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null && auth.getPrincipal() instanceof UserImpl user) {
      return user.getId().toString();
    }
    return "anonymous";
  }

  public void success(String invocation, String message, Object data, Object prev) {

    LoggerContext context = LoggerContextHolder.get();

    String userId = currentUser();

    var event = new LoggerEventSuccess(
        true,
        Instant.now().toEpochMilli(),
        context.getPath(),
        context.getMethod(),
        userId,
        context.getIp(),
        context.getUserAgent(),
        System.currentTimeMillis() - context.getStartMills(),
        serviceName,
        invocation,
        message,
        toJson(data),
        toJson(prev));

    log.trace(toJson(event));
  }

  public void failure(String invocation, String message, BaseException baseException) {

    LoggerContext context = LoggerContextHolder.get();

    String userId = currentUser();

    var event = new LoggerEventFailure(
        false,
        Instant.now().toEpochMilli(),
        context.getPath(),
        context.getMethod(),
        userId,
        context.getIp(),
        context.getUserAgent(),
        System.currentTimeMillis() - context.getStartMills(),
        serviceName,
        invocation,
        baseException.getHttpStatus().name(),
        baseException.getCode(),
        baseException.getMessage());

    log.trace(toJson(event));
  }

  public record LoggerEventSuccess(
      boolean success,
      long timestamp,
      String path,
      String method,
      String userId,
      String ip,
      String userAgent,
      long durationMs,
      String service,
      String invocation,
      String message,
      String data,
      String prev) {
  }

  public record LoggerEventFailure(
      boolean success,
      long timestamp,
      String path,
      String method,
      String userId,
      String ip,
      String userAgent,
      long durationMs,
      String service,
      String invocation,
      String status,
      String code,
      String message) {
  }
}
