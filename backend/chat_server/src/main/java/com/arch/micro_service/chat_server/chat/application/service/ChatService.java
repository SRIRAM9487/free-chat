package com.arch.micro_service.chat_server.chat.application.service;

import com.arch.micro_service.chat_server.chat.domain.entity.Chat;
import com.arch.micro_service.chat_server.chat.infrastructure.dto.request.ChatMessageCreateRequest;

public interface ChatService {

  Chat create(ChatMessageCreateRequest request);

}
