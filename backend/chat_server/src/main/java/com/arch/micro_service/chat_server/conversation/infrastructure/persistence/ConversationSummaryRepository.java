package com.arch.micro_service.chat_server.conversation.infrastructure.persistence;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.ConversationChatGroupDto;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.ConversationChatterDto;

public interface ConversationSummaryRepository {

  List<ConversationChatterDto> getAllChatterForId(Long id);

  List<ConversationChatGroupDto> getAllChatterGroupForId(Long id);

}
