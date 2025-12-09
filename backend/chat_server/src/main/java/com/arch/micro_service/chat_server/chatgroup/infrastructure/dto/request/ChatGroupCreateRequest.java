package com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.request;

public record ChatGroupCreateRequest(
    String name,
    String description) {
}
