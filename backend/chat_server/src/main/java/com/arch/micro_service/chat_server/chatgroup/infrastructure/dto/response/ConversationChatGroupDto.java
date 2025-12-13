package com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response;

import java.util.List;

public record ConversationChatGroupDto(Long id, String name, String last, List<String> members) {
}
