package com.arch.micro_service.chat_server.group.infrastructure.dto.request;

import java.util.List;

public record GroupCreateRequest(
    String name,
    String description,
    List<GroupMemberCreateRequest> groupMemberIds,
    boolean adminOnly) {
}
