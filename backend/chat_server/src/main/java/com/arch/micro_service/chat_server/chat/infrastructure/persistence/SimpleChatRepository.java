package com.arch.micro_service.chat_server.chat.infrastructure.persistence;

import java.util.List;

import com.arch.micro_service.chat_server.chat.domain.entity.SimpleChat;

public interface SimpleChatRepository {

  List<SimpleChat> giveBySenderId(Long id);

  List<SimpleChat> giveByReceiverId(Long id);

  List<SimpleChat> create(SimpleChat chat);

  List<SimpleChat> delete(SimpleChat chat);

  List<SimpleChat> update(SimpleChat chat);

}
