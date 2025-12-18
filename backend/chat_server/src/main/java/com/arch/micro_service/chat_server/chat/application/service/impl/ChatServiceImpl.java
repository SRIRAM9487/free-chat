package com.arch.micro_service.chat_server.chat.application.service.impl;

import com.arch.micro_service.chat_server.chat.application.service.ChatService;
import com.arch.micro_service.chat_server.chat.domain.entity.Chat;
import com.arch.micro_service.chat_server.chat.infrastructure.dto.request.ChatMessageCreateRequest;
import com.arch.micro_service.chat_server.chat.infrastructure.persistence.ChatRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

  private final ChatRepository chatRepository;

  @Override
  public Chat create(ChatMessageCreateRequest request) {
    Chat chat = new Chat();
    chat.setMessage(request.message());
    chat.setGroupId(request.groupId());
    chat.setChatterId(request.chatterId());
    var saved = chatRepository.save(chat);
    return saved;
  }

}
