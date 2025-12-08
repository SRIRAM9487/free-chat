package com.arch.micro_service.chat_server.group.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.arch.micro_service.chat_server.group.domain.entity.ChatGroup;
import com.arch.micro_service.chat_server.group.infrastructure.persistence.impl.ChatGroupRepositoryImpl;
import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ChatGroupRepositoryImplTest extends AbstractTestContainer {

  private final ChatGroupRepositoryImpl chatGroupRepositoryImpl;

  @Autowired
  public ChatGroupRepositoryImplTest(ChatGroupRepositoryImpl chatGroupRepositoryImpl) {
    this.chatGroupRepositoryImpl = chatGroupRepositoryImpl;
  }

  @Test
  void getAll() {
    List<ChatGroup> groups = chatGroupRepositoryImpl.findAll();
    assertEquals(10, groups.size());
  }

  @Test
  void getById() {
    List<ChatGroup> groups = chatGroupRepositoryImpl.findAll();
    assertEquals(10, groups.size());
  }
}
