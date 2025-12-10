package com.arch.micro_service.chat_server.chatgroup.infrastructure.persistence;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.domain.entity.GroupMember;

public interface GroupMemberRepository {

  List<GroupMember> findAll();

  GroupMember findById(Long id);

  GroupMember save(GroupMember groupMember);

  GroupMember update(GroupMember groupMember);

  GroupMember delete(Long id);

}
