package com.arch.micro_service.auth_server.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;

@Component
public class LogUtils {

  private static final ObjectMapper mapper = new ObjectMapper();

  public String success(LogEntry entry) {
    entry.setSuccess(true);
    entry.setTimestamp(Instant.now().toString());
    return toJson(entry);
  }

  public String error(LogEntry entry) {
    entry.setSuccess(false);
    entry.setTimestamp(Instant.now().toString());
    return toJson(entry);
  }

  private String toJson(LogEntry entry) {
    try {
      return mapper.writeValueAsString(entry);
    } catch (Exception e) {
      throw new RuntimeException("Failed to build log JSON", e);
    }
  }

  @Data
  @Builder
  public static class LogEntry {
    private String timestamp;
    private String level;
    private String service;
    private String env;
    private String method;
    private String path;
    private Integer statusCode;
    private Long latencyMs;
    private Long requestSizeBytes;
    private Long responseSizeBytes;
    private Boolean success;

    private String userId;
    private String ip;
    private String xForwardedFor;
    private String userAgent;

    private String traceId;
    private String spanId;
    private String requestId;

    private String message;
    private String hostname;
    private String version;

    private String errorCode;
    private String exception;
    private String stackTrace;

    private Map<String, Object> meta;
  }

}
