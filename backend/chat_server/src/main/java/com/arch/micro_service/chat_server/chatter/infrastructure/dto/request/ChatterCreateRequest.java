package com.arch.micro_service.chat_server.chatter.infrastructure.dto.request;

public record ChatterCreateRequest(String name, Long userId) {
}
