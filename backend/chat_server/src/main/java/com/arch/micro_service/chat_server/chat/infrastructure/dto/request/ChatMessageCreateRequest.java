package com.arch.micro_service.chat_server.chat.infrastructure.dto.request;

public record ChatMessageCreateRequest(String message, Long chatterId, String chatter, Long groupId) {
}
