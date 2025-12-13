package com.arch.micro_service.chat_server.chatgroup.infrastructure.persistence;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.domain.entity.ChatGroup;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.ChatMessage;

public interface ChatGroupRepository {

  List<ChatGroup> findAll();

  ChatGroup findById(Long id);

  ChatGroup save(ChatGroup chatGroup);

  ChatGroup update(ChatGroup chatGroup);

  ChatGroup delete(Long id);

  List<ChatMessage> findMessageByGroupId(Long id);

}
