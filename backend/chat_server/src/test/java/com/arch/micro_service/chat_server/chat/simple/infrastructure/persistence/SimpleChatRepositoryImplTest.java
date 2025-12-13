package com.arch.micro_service.chat_server.chat.simple.infrastructure.persistence;

import com.arch.micro_service.chat_server.chat.infrastructure.persistence.SimpleChatRepository;
import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleChatRepositoryImplTest extends AbstractTestContainer {

  @Autowired
  private SimpleChatRepository simpleChatRepository;

}
