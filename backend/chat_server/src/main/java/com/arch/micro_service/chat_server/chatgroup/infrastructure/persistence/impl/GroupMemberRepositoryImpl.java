package com.arch.micro_service.chat_server.chatgroup.infrastructure.persistence.impl;

import java.util.List;
import com.arch.micro_service.chat_server.chatgroup.domain.entity.AccessLevel;
import com.arch.micro_service.chat_server.chatgroup.domain.entity.GroupMember;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.persistence.GroupMemberRepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GroupMemberRepositoryImpl implements GroupMemberRepository {

  private final JdbcTemplate jdbcTemplate;

  private final RowMapper<GroupMember> mapper = (res, row) -> {
    GroupMember groupMember = new GroupMember();
    groupMember.setId(res.getLong("id"));
    groupMember.setGroupId(res.getLong("group_id"));
    groupMember.setChatterId(res.getLong("chatter_id"));
    groupMember.setAccessLevel(AccessLevel.valueOf(res.getString("access_level")));
    return groupMember;
  };

  @Override
  public List<GroupMember> findAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

  @Override
  public GroupMember findById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public GroupMember save(GroupMember groupMember) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public GroupMember update(GroupMember groupMember) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public GroupMember delete(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

}
