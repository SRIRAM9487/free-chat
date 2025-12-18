package com.arch.micro_service.chat_server.chat.infrastructure.persistence;

import com.arch.micro_service.chat_server.chat.domain.entity.Chat;

public interface ChatRepository {

  Chat save(Chat chat);

}
