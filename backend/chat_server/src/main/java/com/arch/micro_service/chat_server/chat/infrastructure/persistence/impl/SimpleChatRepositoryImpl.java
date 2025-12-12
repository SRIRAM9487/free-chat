package com.arch.micro_service.chat_server.chat.infrastructure.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.arch.micro_service.chat_server.chat.domain.entity.SimpleChat;
import com.arch.micro_service.chat_server.chat.infrastructure.persistence.SimpleChatRepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SimpleChatRepositoryImpl implements SimpleChatRepository {

  private final JdbcTemplate jdbcTemplate;

  private final RowMapper<SimpleChat> mapper = (res, row) -> {
    SimpleChat chat = new SimpleChat();
    chat.setId(res.getLong("id"));
    chat.setMessage(res.getString("message"));
    chat.setSenderId(res.getLong("sender_id"));
    chat.setReceiverId(res.getLong("receiver_id"));

    return chat;
  };

  @Override
  public List<SimpleChat> giveBySenderId(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'giveBySenderId'");
  }

  @Override
  public List<SimpleChat> giveByReceiverId(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'giveByRecieverId'");
  }

  @Override
  public List<SimpleChat> create(SimpleChat chat) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  public List<SimpleChat> delete(SimpleChat chat) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public List<SimpleChat> update(SimpleChat chat) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

}
