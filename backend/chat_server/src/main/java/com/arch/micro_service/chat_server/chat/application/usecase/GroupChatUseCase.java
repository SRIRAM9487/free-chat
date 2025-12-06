package com.arch.micro_service.chat_server.chat.application.usecase;

import com.arch.micro_service.chat_server.chat.domain.entity.GroupChat;
import com.arch.micro_service.chat_server.chat.domain.exception.GroupChatException;
import com.arch.micro_service.chat_server.chat.infrastructure.persistence.GroupChatRepository;
import com.arch.micro_service.chat_server.logger.CustomLogger;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupChatUseCase {

  private final GroupChatRepository groupChatRepository;
  private final CustomLogger customLogger;

  public GroupChat findById(Long id) {

    GroupChat chat = groupChatRepository.findById(id).orElseThrow(() -> {
      var ex = GroupChatException.notFound();
      customLogger.failure("Group chat not found " + id, ex);
      return ex;
    });

    if (!chat.isDeleted()) {
      var ex = GroupChatException.notFound();
      customLogger.failure("Group chat not found " + id, ex);
      throw ex;
    }

    return chat;

  }

}
