package com.arch.micro_service.chat_server.logger;

import java.time.Instant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseException;

@Component
public class CustomLogger {

  private final Logger log = LoggerFactory.getLogger("FileLogger");
  private ObjectMapper mapper;
  private final String serviceName = "Chat Service";

  public CustomLogger() {
    mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }

  private String toJson(Object event) {
    if (event == null) {
      return "{\"error\":\"Failed to serialize log event\"}";
    }
    try {
      return mapper.writeValueAsString(event);
    } catch (JsonProcessingException e) {
      System.out.println(e.getMessage());
      return "{\"error\":\"Failed to serialize log event\"}";
    }
  }

  private String currentUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null && auth.getPrincipal() instanceof UserDetails user) {
      return user.getUsername().toString();
    }
    return "anonymous";
  }

  public void success(String message, Object data, Object prev) {

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
        message,
        toJson(data),
        toJson(prev));

    log.trace(toJson(event));
  }

  public void failure(String message, BaseException baseException) {

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
      String message,
      Object data,
      Object prev) {
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
      String status,
      String code,
      String message) {
  }
}
