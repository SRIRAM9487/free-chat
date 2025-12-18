package com.arch.micro_service.chat_server.chat.infrastructure.persistence.impl;

import com.arch.micro_service.chat_server.chat.domain.entity.Chat;
import com.arch.micro_service.chat_server.chat.infrastructure.persistence.ChatRepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ChatRepositoryImpl implements ChatRepository {

  private final JdbcTemplate jdbcTemplate;

  @Override
  public Chat save(Chat chat) {
    String sql = """
        INSERT INTO chat(chatter_id,group_id,message)
        VALUES(?,?,?)
        RETURNING id,chatter_id,group_id,message,created_at;
        """;
    return jdbcTemplate.queryForObject(sql, (res, row) -> {
      Chat ch = new Chat();
      ch.setId(res.getLong("id"));
      ch.setChatterId(res.getLong("chatter_id"));
      ch.setGroupId(res.getLong("group_id"));
      ch.setMessage(res.getString("message"));
      ch.setCreatedAt(
          res.getTimestamp("created_at") != null ? res.getTimestamp("created_at").toLocalDateTime() : null);
      return ch;
    }, chat.getChatterId(), chat.getGroupId(), chat.getMessage());
  }

}
