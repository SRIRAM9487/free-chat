package com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response;

import java.time.LocalDateTime;

public record GroupMemberResponse(Long id, Long chatterId, Long groupId, String accessLevel, boolean restricted,
    LocalDateTime createdAt,
    String createdBy, LocalDateTime updatedAt, String updatedBy) {
}
