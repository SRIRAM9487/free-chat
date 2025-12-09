package com.arch.micro_service.chat_server.chatgroup.infrastructure.persistence.impl;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.domain.entity.ChatGroup;
import com.arch.micro_service.chat_server.chatgroup.domain.exception.ChatGroupException;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.persistence.ChatGroupRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChatGroupRepositoryImpl implements ChatGroupRepository {

  private final JdbcTemplate jdbcTemplate;

  private final RowMapper<ChatGroup> mapper = (res, rowNum) -> {
    ChatGroup cg = new ChatGroup();
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
    String sql = "SELECT * FROM chat_group WHERE deleted_at IS NULL";
    return jdbcTemplate.query(sql, mapper);
  }

  @Override
  public ChatGroup findById(Long id) {
    String sql = "SELECT * FROM chat_group WHERE id = ? AND deleted_at IS NULL";
    var list = jdbcTemplate.query(sql, mapper, id);

    if (list.isEmpty()) {
      var ex = ChatGroupException.notFound();
      throw ex;
    }

    return list.getFirst();
  }

  @Override
  public ChatGroup save(ChatGroup chatGroup) {

    String sql = """
            INSERT INTO chat_group (name, description, created_by)
            VALUES (?, ?, ?)
            RETURNING id, name, description, created_at, created_by, updated_at, updated_by;
        """;

    return jdbcTemplate.queryForObject(sql, mapper, chatGroup.getName(), chatGroup.getDescription(),
        chatGroup.getCreatedBy());
  }

  @Override
  public ChatGroup update(ChatGroup chatGroup) {
    String sql = """
            UPDATE chat_group
               SET name = ?,
                   description = ?,
                   updated_at = NOW(),
                   updated_by = ?
            WHERE id = ? AND deleted_at IS NULL
            RETURNING id,name,description,created_at,created_by,updated_at,updated_by;
        """;
    try {
      return jdbcTemplate.queryForObject(sql, mapper,
          chatGroup.getName(),
          chatGroup.getDescription(),
          chatGroup.getUpdatedBy(),
          chatGroup.getId());
    } catch (EmptyResultDataAccessException e) {
      throw ChatGroupException.notFound();
    }
  }

}
