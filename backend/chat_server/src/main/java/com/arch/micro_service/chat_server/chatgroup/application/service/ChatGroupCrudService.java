package com.arch.micro_service.chat_server.chatgroup.application.service;

import java.util.List;

import com.arch.micro_service.chat_server.chat.domain.entity.Chat;
import com.arch.micro_service.chat_server.chatgroup.domain.entity.ChatGroup;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.request.ChatGroupCreateRequest;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.ChatMessage;

public interface ChatGroupCrudService {

  List<ChatGroup> findAll();

  ChatGroup findById(Long id);

  ChatGroup create(ChatGroupCreateRequest request);

  ChatGroup update(Long id, ChatGroupCreateRequest request);

  ChatGroup delete(Long id);

  List<ChatMessage> findMessageByGroupId(Long id);
}
