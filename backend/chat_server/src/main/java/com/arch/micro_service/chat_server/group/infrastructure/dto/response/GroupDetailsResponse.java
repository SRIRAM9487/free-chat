package com.arch.micro_service.chat_server.group.infrastructure.dto.response;

import java.util.List;

public record GroupDetailsResponse(
    Long id,
    String name,
    String description,
    List<GroupMemberDetailsResponse> groupMembers,
    boolean adminOnly) {
}
