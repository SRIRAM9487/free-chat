package com.arch.micro_service.chat_server.chat.application.usecase;

import com.arch.micro_service.chat_server.chat.domain.entity.SimpleChat;
import com.arch.micro_service.chat_server.chat.domain.exception.SimpleChatException;
import com.arch.micro_service.chat_server.chat.infrastructure.persistence.SimpleChatRepository;
import com.arch.micro_service.chat_server.logger.CustomLogger;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SimpleChatUseCase {

  private final SimpleChatRepository simpleChatRepository;
  private final CustomLogger customLogger;

  public SimpleChat findById(Long id) {

    var chat = simpleChatRepository.findById(id).orElseThrow(() -> {
      var ex = SimpleChatException.notFound();
      customLogger.failure("Simple chat not found " + id, ex);
      return ex;
    });

    if (!chat.isDeleted()) {
      var ex = SimpleChatException.notFound();
      customLogger.failure("Simple chat not found " + id, ex);
      throw ex;
    }

    return chat;

  }

}
