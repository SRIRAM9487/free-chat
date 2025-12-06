package com.arch.micro_service.chat_server.group.application.usecase;

import com.arch.micro_service.chat_server.group.domain.entity.GroupMember;
import com.arch.micro_service.chat_server.group.domain.exception.GroupException;
import com.arch.micro_service.chat_server.group.domain.exception.GroupMemberException;
import com.arch.micro_service.chat_server.group.infrastructure.persistence.GroupMemberRepository;
import com.arch.micro_service.chat_server.logger.CustomLogger;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupMemberFindUseCase {

  private final GroupMemberRepository groupRepository;
  private final CustomLogger logger;

  public GroupMember findById(Long id) {

    var group = groupRepository.findById(id).orElseThrow(() -> {
      var ex = GroupMemberException.notFound();
      logger.failure("Group Member not found " + id, ex);
      return ex;
    });

    if (group.isDeleted()) {
      var ex = GroupException.notFound();
      logger.failure("Group Member not found " + id, ex);
      throw ex;
    }

    return group;

  }

}
