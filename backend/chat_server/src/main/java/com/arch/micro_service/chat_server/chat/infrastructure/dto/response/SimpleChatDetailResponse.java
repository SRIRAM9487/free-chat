package com.arch.micro_service.chat_server.chat.infrastructure.dto.response;

import java.time.LocalDateTime;

public record SimpleChatDetailResponse(Long id, String message, boolean read, LocalDateTime createdAt) {
}
