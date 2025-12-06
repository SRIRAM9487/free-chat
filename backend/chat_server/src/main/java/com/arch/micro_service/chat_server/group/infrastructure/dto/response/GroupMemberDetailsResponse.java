package com.arch.micro_service.chat_server.group.infrastructure.dto.response;

public record GroupMemberDetailsResponse(
    Long id,
    Long chatterId,
    String accessLevel) {
}
