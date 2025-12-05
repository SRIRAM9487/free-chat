package com.arch.micro_service.chat_server.group.infrastructure.dto.response;

public record GroupDetailsResponse(
    String id,
    String name,
    String description) {
}
