package com.arch.micro_service.chat_server.chat.infrastructure.dto.response;

import java.time.LocalDateTime;

public record GroupChatDetailResponse(Long id, String message, LocalDateTime createdAt) {
}
