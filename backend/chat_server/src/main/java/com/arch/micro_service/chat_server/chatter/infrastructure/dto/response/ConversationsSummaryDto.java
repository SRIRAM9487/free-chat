package com.arch.micro_service.chat_server.chatter.infrastructure.dto.response;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.ConversationChatGroupDto;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.ConversationChatterDto;

public record ConversationsSummaryDto(
    List<ConversationChatterDto> single,
    List<ConversationChatGroupDto> group) {
}
