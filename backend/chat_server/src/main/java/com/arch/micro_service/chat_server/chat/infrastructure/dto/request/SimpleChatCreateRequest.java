package com.arch.micro_service.chat_server.chat.infrastructure.dto.request;

public record SimpleChatCreateRequest(String message, Long senderId, Long receiverId) {
}
