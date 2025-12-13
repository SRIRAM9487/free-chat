package com.arch.micro_service.chat_server.conversation.infrastructure.persistence;

import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import org.springframework.beans.factory.annotation.Autowired;

public class ConversationSummaryRepositoryTest extends AbstractTestContainer {

  @Autowired
  private ConversationSummaryRepository conversationSummaryRepository;

}
