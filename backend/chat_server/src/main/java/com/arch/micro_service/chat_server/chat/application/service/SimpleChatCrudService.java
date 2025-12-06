package com.arch.micro_service.chat_server.chat.application.service;

import java.util.List;

import com.arch.micro_service.chat_server.chat.domain.entity.SimpleChat;
import com.arch.micro_service.chat_server.chat.infrastructure.dto.request.SimpleChatCreateRequest;

public interface SimpleChatCrudService {

  List<SimpleChat> getAll();

  SimpleChat get(String id);

  SimpleChat create(SimpleChatCreateRequest requestDto);

  SimpleChat update(String id, SimpleChatCreateRequest requestDto);

  SimpleChat delete(String id);

}
