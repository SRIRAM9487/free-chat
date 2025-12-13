package com.arch.micro_service.chat_server.conversation.infrastructure.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.ConversationChatGroupDto;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.ConversationChatterDto;
import com.arch.micro_service.chat_server.conversation.infrastructure.persistence.ConversationSummaryRepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ConversationSummaryRepositoryImpl implements ConversationSummaryRepository {

  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<ConversationChatterDto> getAllChatterForId(Long chatterId) {

    String sql = """
        SELECT
            c.id AS chatter_id,
            c.name AS chatter_name,
            sc.message AS last_message
        FROM simple_chat sc
        JOIN chatter c
          ON c.id = CASE
              WHEN sc.sender_id = ? THEN sc.receiver_id
              ELSE sc.sender_id
          END
        WHERE sc.sender_id = ?
           OR sc.receiver_id = ?
        ORDER BY sc.created_at DESC
        """;

    return jdbcTemplate.query(
        sql,
        (rs, rowNum) -> new ConversationChatterDto(
            rs.getLong("chatter_id"),
            rs.getString("chatter_name"),
            rs.getString("last_message")),
        chatterId, chatterId, chatterId);
  }

  @Override
  public List<ConversationChatGroupDto> getAllChatterGroupForId(Long id) {
    String sql = """
        """;

    List<ConversationChatGroupDto> chatters = jdbcTemplate.query(sql, (res, row) -> {
      String name = "";
      Long groupId = 0L;
      String last = "";
      List<String> members = new ArrayList<>();
      return new ConversationChatGroupDto(groupId, name, last, members);
    });

    return chatters;
  }
}
