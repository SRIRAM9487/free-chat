package com.arch.micro_service.chat_server.chatgroup.application.service.impl;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.application.service.GroupMemberService;
import com.arch.micro_service.chat_server.chatgroup.domain.entity.AccessLevel;
import com.arch.micro_service.chat_server.chatgroup.domain.entity.GroupMember;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.request.GroupMemberCreateRequest;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.persistence.GroupMemberRepository;
import com.arch.micro_service.chat_server.logger.event.LogSuccessEvent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupMemberServiceImpl implements GroupMemberService {

  private final GroupMemberRepository groupMemberRepository;
  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public GroupMember findById(Long id) {
    return groupMemberRepository.findById(id);
  }

  @Override
  public List<GroupMember> findByGroupId(Long id) {
    return groupMemberRepository.findByGroupId(id);
  }

  @Override
  public GroupMember create(GroupMemberCreateRequest request) {
    GroupMember groupMember = new GroupMember();
    groupMember.setChatterId(request.chatterId());
    groupMember.setGroupId(request.groupId());
    groupMember.setRestricted(request.restricted());
    groupMember.setAccessLevel(AccessLevel.valueOf(request.accessLevel()));
    var saved = groupMemberRepository.save(groupMember);
    applicationEventPublisher
        .publishEvent(new LogSuccessEvent("Group Member Created", "New", saved, this));
    return saved;
  }

  @Override
  public GroupMember restrict(Boolean restrict, Long id) {
    GroupMember toggled = groupMemberRepository.restrict(restrict, id);
    applicationEventPublisher
        .publishEvent(new LogSuccessEvent("Group Member restriction updated", "RESTRICTION UPDATE", toggled, this));
    return toggled;
  }

  @Override
  public GroupMember access(String level, Long id) {
    GroupMember toggled = groupMemberRepository.access(AccessLevel.valueOf(level), id);
    applicationEventPublisher
        .publishEvent(new LogSuccessEvent("Group Member access updated", "ACCESS UPDATE", toggled, this));
    return toggled;
  }

  @Override
  public GroupMember delete(Long id) {
    GroupMember deleted = groupMemberRepository.delete(id);
    applicationEventPublisher.publishEvent(new LogSuccessEvent("Group Member Deleted", "DELETED", deleted, this));
    return deleted;
  }

  @Override
  public List<GroupMember> deleteByGroupId(Long id) {
    List<GroupMember> deleted = groupMemberRepository.deleteByGroupId(id);
    for (var member : deleted) {
      applicationEventPublisher.publishEvent(new LogSuccessEvent("Group Member Deleted", "DELETED", member, this));
    }
    return deleted;
  }

}
