package com.arch.micro_service.chat_server.group.infrastructure.persistence.impl;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import com.arch.micro_service.chat_server.group.domain.entity.ChatGroup;
import com.arch.micro_service.chat_server.group.infrastructure.persistence.ChatGroupRepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChatGroupRepositoryImpl implements ChatGroupRepository {

  private final JdbcTemplate jdbcTemplate;

  RowMapper<ChatGroup> mapper = (ResultSet res, int rowNum) -> {
    var cg = new ChatGroup();
    cg.setId(res.getLong("id"));
    cg.setName(res.getString("name"));
    cg.setDescription(res.getString("description"));
    cg.setCreatedAt(res.getTimestamp("created_at") != null ? res.getTimestamp("created_at").toLocalDateTime() : null);
    cg.setCreatedBy(res.getString("created_by"));
    cg.setUpdatedAt(res.getTimestamp("updated_at") != null ? res.getTimestamp("updated_at").toLocalDateTime() : null);
    cg.setUpdatedBy(res.getString("updated_by"));
    return cg;
  };

  @Override
  public List<ChatGroup> findAll() {
    String sql = "SELECT * FROM chat_group WHERE deleted_at is NULL";
    return jdbcTemplate.query(sql, mapper);
  }

  @Override
  public Optional<ChatGroup> findById(Long id) {
    String sql = "SELECT * FROM chat_group WHERE id = ? AND deleted_at is NULL";
    var res = jdbcTemplate.query(sql, mapper, id).getFirst();
    return Optional.of(res);
  }

  @Override
  public List<ChatGroup> update(ChatGroup chatGroup) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public List<ChatGroup> save(ChatGroup chatGroup) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public List<ChatGroup> saveAll(List<ChatGroup> chatGroups) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
  }

}
