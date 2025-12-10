package com.arch.micro_service.chat_server.chatgroup.application.service;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.domain.entity.GroupMember;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.request.GroupMemberCreateRequest;

public interface GroupMemberService {

  GroupMember findById(Long id);

  List<GroupMember> findByGroupId(Long id);

  GroupMember create(GroupMemberCreateRequest request);

  GroupMember restrict(Boolean restrict, Long id);

  GroupMember access(String level, Long id);

  GroupMember delete(Long id);

  List<GroupMember> deleteByGroupId(Long id);

}
