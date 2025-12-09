package com.arch.micro_service.chat_server.chatgroup.application.service.impl;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.application.service.ChatGroupService;
import com.arch.micro_service.chat_server.chatgroup.domain.entity.ChatGroup;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.request.ChatGroupCreateRequest;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.persistence.ChatGroupRepository;
import com.arch.micro_service.chat_server.logger.context.MetaContextHolder;
import com.arch.micro_service.chat_server.logger.event.LogSuccessEvent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatGroupServiceImpl implements ChatGroupService {

  private final ChatGroupRepository chatGroupRepository;
  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public List<ChatGroup> findAll() {
    return chatGroupRepository.findAll();
  }

  @Override
  public ChatGroup findById(String id) {
    return chatGroupRepository.findById(Long.parseLong(id));
  }

  @Override
  public ChatGroup save(ChatGroupCreateRequest request) {
    var cg = new ChatGroup();
    cg.setName(request.name());
    cg.setDescription(request.description());
    cg.setCreatedBy(MetaContextHolder.get().getUserId());
    var savedChatGroup = chatGroupRepository.save(cg);
    applicationEventPublisher.publishEvent(new LogSuccessEvent("Chat Group Created", "New", savedChatGroup, this));
    return savedChatGroup;
  }

  @Override
  public ChatGroup update(String id, ChatGroupCreateRequest request) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public ChatGroup delete(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

}
