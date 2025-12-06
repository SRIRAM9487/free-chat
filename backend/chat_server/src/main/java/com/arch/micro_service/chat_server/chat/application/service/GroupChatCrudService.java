package com.arch.micro_service.chat_server.chat.application.service;

import java.util.List;

import com.arch.micro_service.chat_server.chat.domain.entity.GroupChat;
import com.arch.micro_service.chat_server.chat.infrastructure.dto.request.GroupChatCreateRequest;

public interface GroupChatCrudService {

  List<GroupChat> getAll();

  GroupChat get(String id);

  GroupChat create(GroupChatCreateRequest requestDto);

  GroupChat update(String id, GroupChatCreateRequest requestDto);

  GroupChat delete(String id);

}
