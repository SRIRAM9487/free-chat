package com.arch.micro_service.chat_server.chatter.infrastructure.dto.response;

import java.time.LocalDateTime;

public record ChatterResponse(
    Long id,
    String name,
    Long userId,
    LocalDateTime createdAt,
    String createdBy,
    LocalDateTime updatedAt,
    String updatedBy) {
}
