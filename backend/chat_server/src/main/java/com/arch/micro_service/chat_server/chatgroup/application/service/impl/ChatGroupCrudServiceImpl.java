package com.arch.micro_service.chat_server.chatgroup.application.service.impl;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.application.service.ChatGroupCrudService;
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
public class ChatGroupCrudServiceImpl implements ChatGroupCrudService {

  private final ChatGroupRepository chatGroupRepository;
  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public List<ChatGroup> findAll() {
    return chatGroupRepository.findAll();
  }

  @Override
  public ChatGroup findById(Long id) {
    return chatGroupRepository.findById(id);
  }

  @Override
  public ChatGroup create(ChatGroupCreateRequest request) {
    var cg = new ChatGroup();
    cg.setName(request.name());
    cg.setDescription(request.description());
    cg.setCreatedBy(MetaContextHolder.get().getUserId());
    var savedChatGroup = chatGroupRepository.save(cg);
    applicationEventPublisher.publishEvent(new LogSuccessEvent("Chat Group Created", "New", savedChatGroup, this));
    return savedChatGroup;
  }

  @Override
  public ChatGroup update(Long id, ChatGroupCreateRequest request) {
    var cg = new ChatGroup();
    cg.setId(id);
    cg.setName(request.name());
    cg.setDescription(request.description());
    cg.setCreatedBy(MetaContextHolder.get().getUserId());
    var updatedChatGroup = chatGroupRepository.update(cg);
    applicationEventPublisher
        .publishEvent(new LogSuccessEvent("Chat Group Updated", cg, updatedChatGroup, this));
    return updatedChatGroup;
  }

  @Override
  public ChatGroup delete(Long id) {
    var chatGroup = chatGroupRepository.delete(id);
    applicationEventPublisher
        .publishEvent(new LogSuccessEvent("Chat Group Deleted", "Deleted", chatGroup, this));
    return chatGroup;
  }

}
