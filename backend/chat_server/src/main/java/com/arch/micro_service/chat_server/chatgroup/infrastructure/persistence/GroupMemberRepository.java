package com.arch.micro_service.chat_server.chatgroup.infrastructure.persistence;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.domain.entity.AccessLevel;
import com.arch.micro_service.chat_server.chatgroup.domain.entity.GroupMember;

public interface GroupMemberRepository {

  GroupMember findById(Long id);

  List<GroupMember> findByGroupId(Long id);

  GroupMember save(GroupMember groupMember);

  GroupMember restrict(Boolean restrict, Long id);

  GroupMember access(AccessLevel level, Long id);

  GroupMember delete(Long id);

  List<GroupMember> deleteByGroupId(Long id);

}
