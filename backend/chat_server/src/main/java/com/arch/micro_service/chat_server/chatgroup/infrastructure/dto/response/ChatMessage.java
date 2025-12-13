package com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response;

import java.time.LocalDateTime;

public record ChatMessage(Long id, String message, String chatter, LocalDateTime createdAt) {
}
