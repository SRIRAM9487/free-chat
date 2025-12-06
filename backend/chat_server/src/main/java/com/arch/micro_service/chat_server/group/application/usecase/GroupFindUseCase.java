package com.arch.micro_service.chat_server.group.application.usecase;

import com.arch.micro_service.chat_server.group.domain.entity.Group;
import com.arch.micro_service.chat_server.group.domain.exception.GroupException;
import com.arch.micro_service.chat_server.group.infrastructure.persistence.GroupRepository;
import com.arch.micro_service.chat_server.logger.CustomLogger;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupFindUseCase {

  private final GroupRepository groupRepository;
  private final CustomLogger logger;

  public Group findById(Long id) {

    var group = groupRepository.findById(id).orElseThrow(() -> {
      var ex = GroupException.notFound();
      logger.failure("Group not found " + id, ex);
      return ex;
    });

    if (group.isDeleted()) {
      var ex = GroupException.notFound();
      logger.failure("Group not found " + id, ex);
      throw ex;
    }

    return group;

  }

}
