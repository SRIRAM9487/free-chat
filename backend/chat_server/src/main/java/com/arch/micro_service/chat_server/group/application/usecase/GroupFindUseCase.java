package com.arch.micro_service.chat_server.group.application.usecase;

import com.arch.micro_service.chat_server.group.domain.entity.Group;
import com.arch.micro_service.chat_server.group.domain.exception.GroupException;
import com.arch.micro_service.chat_server.group.infrastructure.persistence.GroupRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupFindUseCase {

  private final GroupRepository groupRepository;

  public Group findById(Long id) {

    var group = groupRepository.findById(id).orElseThrow(() -> {
      return GroupException.notFound();
    });

    if (group.isDeleted()) {
      var ex = GroupException.notFound();
      throw ex;
    }

    return group;

  }

}
