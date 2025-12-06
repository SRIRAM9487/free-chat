package com.arch.micro_service.chat_server.group.infrastructure.dto.response;

public record GroupDetailsResponse(
    Long id,
    String name,
    String description,
    boolean adminOnly) {
}
