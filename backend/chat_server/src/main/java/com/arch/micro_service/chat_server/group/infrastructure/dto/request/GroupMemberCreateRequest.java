package com.arch.micro_service.chat_server.group.infrastructure.dto.request;

public record GroupMemberCreateRequest(
    Long chatterId,
    String accessLevel) {
}
