package com.arch.micro_service.chat_server.chatter.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.arch.micro_service.chat_server.chatter.application.service.impl.ChatterCrudServiceImpl;
import com.arch.micro_service.chat_server.chatter.domain.entity.Chatter;
import com.arch.micro_service.chat_server.chatter.domain.exception.ChatterException;
import com.arch.micro_service.chat_server.chatter.domain.exception.type.ChatterExceptionType;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.request.ChatterCreateRequest;
import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

public class ChatterCrudServiceImplTest extends AbstractTestContainer {

  @Autowired
  private ChatterCrudServiceImpl crudServiceImpl;

  @Test
  void getAll() {
    List<Chatter> chatters = crudServiceImpl.getAll();
    assertEquals(10, chatters.size());
  }

  @Test
  void getById() {
    Chatter chatter = crudServiceImpl.get("1");
    assertEquals(1L, chatter.getId());
    assertEquals(1L, chatter.getUserId());
    assertEquals(false, chatter.isBanned());
    assertEquals("CHATTER_1", chatter.getName());
  }

  @Test
  @Transactional
  void getById_NotFound() {
    ChatterException chatterException = assertThrowsExactly(ChatterException.class, () -> {
      crudServiceImpl.get("99999999");
    });
    assertEquals(ChatterExceptionType.CHATTER_NOT_FOUND.name(), chatterException.getCode(), "Chatter not found");
    crudServiceImpl.delete("1");
    chatterException = assertThrowsExactly(ChatterException.class, () -> {
      crudServiceImpl.get("1");
    });
    assertEquals(ChatterExceptionType.CHATTER_NOT_FOUND.name(), chatterException.getCode(), "Chatter not found");
  }

  @Test
  @Transactional
  void create_success() {
    var req = new ChatterCreateRequest("TEST_CHATTER", 11L);
    Chatter chatter = crudServiceImpl.create(req);
    assertEquals(req.userId(), chatter.getUserId());
    assertEquals(req.name(), chatter.getName());
    assertNotNull(chatter.getCreatedAt());
    assertNull(chatter.getUpdatedAt());
    assertNull(chatter.getDeletedAt());
  }

  @Test
  @Transactional
  void create_userIdExists() {
    var req = new ChatterCreateRequest("TEST_CHATTER", 1L);
    DataIntegrityViolationException exception = assertThrowsExactly(
        DataIntegrityViolationException.class, () -> {
          crudServiceImpl.create(req);
        }, "userId must be unique");
    assertTrue(exception.getLocalizedMessage().contains("chatters_user_id_key"));
  }

  @Test
  @Transactional
  void update_success() {
    var req = new ChatterCreateRequest("TEST_CHATTER", 2L);
    Chatter chatter = crudServiceImpl.update("1", req);
    assertEquals(req.userId(), chatter.getUserId());
    assertEquals(req.name(), chatter.getName());
    assertNotNull(chatter.getCreatedAt());
    assertNotNull(chatter.getUpdatedAt());
  }

  // @Test
  // @Transactional
  // void update_userIdExists() {
  // var req = new ChatterCreateRequest("TEST_CHATTER", 1L);
  // DataIntegrityViolationException exception = assertThrowsExactly(
  // DataIntegrityViolationException.class, () -> {
  // crudServiceImpl.update("2", req);
  // }, "userId must be unique");
  // assertTrue(exception.getLocalizedMessage().contains("chatters_user_id_key"));
  // }

  @Test
  @Transactional
  void delete() {
    int beforeDelete = crudServiceImpl.getAll().size();
    Chatter chat = crudServiceImpl.delete("1");
    assertNotNull(chat.getDeletedAt());
    int aftereDelete = crudServiceImpl.getAll().size();
    assertEquals(beforeDelete - 1, aftereDelete);
  }

}
