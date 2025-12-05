package com.arch.micro_service.chat_server.chat.infrastructure.dto.request;

public record GroupChatCreateRequest(
    String message,
    String chatterId) {
}
