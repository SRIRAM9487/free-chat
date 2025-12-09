package com.arch.micro_service.chat_server.chatgroup.application.service;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.domain.entity.ChatGroup;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.request.ChatGroupCreateRequest;

public interface ChatGroupService {

  List<ChatGroup> findAll();

  ChatGroup findById(String id);

  ChatGroup save(ChatGroupCreateRequest request);

  ChatGroup update(String id, ChatGroupCreateRequest request);

  ChatGroup delete(String id);
}
