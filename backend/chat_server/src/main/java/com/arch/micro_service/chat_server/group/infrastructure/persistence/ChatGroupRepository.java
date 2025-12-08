package com.arch.micro_service.chat_server.group.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import com.arch.micro_service.chat_server.group.domain.entity.ChatGroup;

public interface ChatGroupRepository {

  List<ChatGroup> findAll();

  Optional<ChatGroup> findById(Long id);

  List<ChatGroup> update(ChatGroup chatGroup);

  List<ChatGroup> save(ChatGroup chatGroup);

  List<ChatGroup> saveAll(List<ChatGroup> chatGroups);

}
