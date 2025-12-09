package com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response;

import java.time.LocalDateTime;

public record ChatGroupResponse(
    Long id,
    String name,
    String description,
    LocalDateTime createdAt,
    String createdBy,
    LocalDateTime updatedAt,
    String updatedBy) {

}
