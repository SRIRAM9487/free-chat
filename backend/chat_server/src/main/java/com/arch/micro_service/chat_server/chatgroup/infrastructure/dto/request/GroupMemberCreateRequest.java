package com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.request;

public record GroupMemberCreateRequest(Long chatterId, Long groupId, String accessLevel, Boolean restricted) {
}
