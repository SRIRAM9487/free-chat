package com.arch.micro_service.chat_server.logger.dto;

import java.time.LocalDateTime;

public record AuditLogException(
    boolean success,
    LocalDateTime timestamp,
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
