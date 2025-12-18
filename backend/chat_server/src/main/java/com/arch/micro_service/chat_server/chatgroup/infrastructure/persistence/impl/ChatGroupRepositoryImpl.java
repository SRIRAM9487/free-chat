package com.arch.micro_service.chat_server.chatgroup.infrastructure.persistence.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.arch.micro_service.chat_server.chat.domain.entity.Chat;
import com.arch.micro_service.chat_server.chatgroup.domain.entity.ChatGroup;
import com.arch.micro_service.chat_server.chatgroup.domain.exception.ChatGroupException;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.ChatMessage;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.persistence.ChatGroupRepository;
import com.arch.micro_service.chat_server.logger.context.MetaContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
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

  private final RowMapper<ChatGroup> deleteMapper = (res, rowNum) -> {
    ChatGroup cg = new ChatGroup();
    cg.setId(res.getLong("id"));
    cg.setName(res.getString("name"));
    cg.setDescription(res.getString("description"));
    cg.setCreatedAt(res.getTimestamp("created_at") != null ? res.getTimestamp("created_at").toLocalDateTime() : null);
    cg.setCreatedBy(res.getString("created_by"));
    cg.setUpdatedAt(res.getTimestamp("updated_at") != null ? res.getTimestamp("updated_at").toLocalDateTime() : null);
    cg.setUpdatedBy(res.getString("updated_by"));
    cg.setDeletedAt(res.getTimestamp("deleted_at") != null ? res.getTimestamp("deleted_at").toLocalDateTime() : null);
    cg.setDeletedBy(res.getString("deleted_by"));
    return cg;
  };

  @Override
  public List<ChatGroup> findAll() {
    String sql = """
        SELECT id,name,description,created_at,created_by,updated_at,updated_by FROM chat_group
        WHERE deleted_at IS NULL
        """;
    return jdbcTemplate.query(sql, mapper);
  }

  @Override
  public ChatGroup findById(Long id) {
    String sql = """
        SELECT id,name,description,created_at,created_by,updated_at,updated_by FROM chat_group
        WHERE id = ? AND deleted_at IS NULL
        """;
    try {
      return jdbcTemplate.queryForObject(sql, mapper, id);
    } catch (EmptyResultDataAccessException e) {
      throw ChatGroupException.notFound();
    }
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

  @Override
  public ChatGroup delete(Long id) {
    String sql = """
        UPDATE chat_group
        SET deleted_at = NOW(),
        deleted_by = ?
        WHERE id = ? AND deleted_at IS NULL
        RETURNING id,name,description,created_at,created_by,updated_at,updated_by,deleted_at,deleted_by;
        """;
    try {
      return jdbcTemplate.queryForObject(sql, deleteMapper, MetaContextHolder.get().getUserId(), id);
    } catch (EmptyResultDataAccessException e) {
      throw ChatGroupException.notFound();
    }
  }

  @Override
  public List<ChatMessage> findMessageByGroupId(Long id) {
    String sql = """
        SELECT
        c.message AS message,
        c.created_at AS created_at,
        ch.id AS chatter_id,
        ch.name AS chatter_name
        FROM chat c
        JOIN chatter ch ON ch.id = c.chatter_id
        WHERE c.group_id = ?
        ORDER BY c.created_at ASC
        LIMIT 50
        """;
    List<ChatMessage> chats = jdbcTemplate.query(sql, (res, row) -> {
      String message = res.getString("message");
      String chatter = res.getString("chatter_name");
      LocalDateTime createdAt = res.getTimestamp("created_at").toLocalDateTime();
      return new ChatMessage(message, chatter, createdAt);
    }, id);
    return chats;
  }

}
