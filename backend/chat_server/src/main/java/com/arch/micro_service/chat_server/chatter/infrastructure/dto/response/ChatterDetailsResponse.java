package com.arch.micro_service.chat_server.chatter.infrastructure.dto.response;

public record ChatterDetailsResponse(
    Long id,
    Long userId,
    String name) {
}
