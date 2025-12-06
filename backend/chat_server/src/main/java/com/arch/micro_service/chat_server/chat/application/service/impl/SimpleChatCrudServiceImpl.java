package com.arch.micro_service.chat_server.chat.application.service.impl;

import java.util.List;

import com.arch.micro_service.chat_server.chat.application.service.SimpleChatCrudService;
import com.arch.micro_service.chat_server.chat.application.usecase.SimpleChatUseCase;
import com.arch.micro_service.chat_server.chat.domain.entity.SimpleChat;
import com.arch.micro_service.chat_server.chat.infrastructure.dto.request.SimpleChatCreateRequest;
import com.arch.micro_service.chat_server.chat.infrastructure.persistence.SimpleChatRepository;
import com.arch.micro_service.chat_server.chatter.application.usecase.ChatterFindUseCase;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SimpleChatCrudServiceImpl implements SimpleChatCrudService {

  private final SimpleChatRepository simpleChatRepository;
  private final SimpleChatUseCase simpleChatUseCase;
  private final ChatterFindUseCase chatterFindUseCase;

  @Override
  public List<SimpleChat> getAll() {
    return simpleChatRepository.findAll().stream().filter(c -> !c.isDeleted()).toList();
  }

  @Override
  public SimpleChat get(String id) {
    return simpleChatUseCase.findById(Long.parseLong(id));
  }

  @Override
  public SimpleChat create(SimpleChatCreateRequest requestDto) {
    var chat = new SimpleChat();
    chat.setMessage(requestDto.message());
    chat.setSender(chatterFindUseCase.findById(requestDto.senderId()));
    chat.setReceiver(chatterFindUseCase.findById(requestDto.receiverId()));
    var savedChat = simpleChatRepository.save(chat);
    return savedChat;
  }

  @Override
  public SimpleChat update(String id, SimpleChatCreateRequest requestDto) {
    var chat = simpleChatUseCase.findById(Long.parseLong(id));
    chat.setMessage(requestDto.message());
    var updatedChat = simpleChatRepository.save(chat);
    return updatedChat;
  }

  @Override
  public SimpleChat delete(String id) {
    var chat = simpleChatUseCase.findById(Long.parseLong(id));
    chat.softDelete();
    var savedChat = simpleChatRepository.save(chat);
    return savedChat;
  }

}
