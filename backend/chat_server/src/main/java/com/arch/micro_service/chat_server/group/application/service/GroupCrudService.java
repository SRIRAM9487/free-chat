package com.arch.micro_service.chat_server.group.application.service;

import java.util.List;

import com.arch.micro_service.chat_server.group.domain.entity.Group;
import com.arch.micro_service.chat_server.group.infrastructure.dto.request.GroupCreateRequest;

public interface GroupCrudService {

  List<Group> getAll();

  Group get(String id);

  Group create(GroupCreateRequest requestDto);

  Group update(String id, GroupCreateRequest requestDto);

  Group delete(String id);
}
