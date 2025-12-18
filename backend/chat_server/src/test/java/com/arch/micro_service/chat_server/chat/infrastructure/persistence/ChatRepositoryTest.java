package com.arch.micro_service.chat_server.chat.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.arch.micro_service.chat_server.chat.domain.entity.Chat;
import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ChatRepositoryTest extends AbstractTestContainer {

  @Autowired
  private ChatRepository chatRepository;

  @Test
  @Transactional
  void create() {
    Chat chat = new Chat();
    chat.setMessage("THIS IS AN TEST MESSAGE");
    chat.setGroupId(1L);
    chat.setChatterId(1L);

    var saved = chatRepository.save(chat);

    assertNotNull(saved.getId());
    assertEquals(chat.getGroupId(), saved.getGroupId());
    assertEquals(chat.getChatterId(), saved.getChatterId());
    assertEquals(chat.getMessage(), saved.getMessage());
    assertNotNull(saved.getCreatedAt());

  }
}
