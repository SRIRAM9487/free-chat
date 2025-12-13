package com.arch.micro_service.chat_server.chatter.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.util.List;

import com.arch.micro_service.chat_server.chatter.domain.entity.Chatter;
import com.arch.micro_service.chat_server.chatter.domain.exception.ChatterException;
import com.arch.micro_service.chat_server.chatter.domain.exception.type.ChatterExceptionType;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.request.ChatterCreateRequest;
import com.arch.micro_service.chat_server.logger.context.MetaContext;
import com.arch.micro_service.chat_server.logger.context.MetaContextHolder;
import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

public class ChatterCrudServiceTest extends AbstractTestContainer {

  @Autowired
  private ChatterCrudService chatterCrudService;
  @MockitoBean
  private ApplicationEventPublisher applicationEventPublisher;
  private final Long notFoundId =9999L;

  @BeforeEach
  void setup() {
    MetaContextHolder.set(new MetaContext("Test user", "/test/path", "TEST PAT", 1000, "1.2.3.1", "FIRE FOX"));
    doNothing().when(applicationEventPublisher).publishEvent(any());
  }

  @AfterEach
  void cleanup() {
    MetaContextHolder.clear();
  }

  @Test
  @Transactional
  void getAll() {
    List<Chatter> chatters = chatterCrudService.findAll();
assertFalse(chatters.isEmpty());  }

  @Test
  @Transactional
  void getById() {
    Chatter chatter = chatterCrudService.findById(1L);
    assertEquals(1L, chatter.getId());
    assertEquals(1L, chatter.getUserId());
    assertNotNull(chatter.getName());
  }

  @Test
  @Transactional
  void getById_notFound_exception() {
    ChatterException ex = assertThrowsExactly(ChatterException.class, () -> {
      chatterCrudService.findById(9991L);
    });
    assertEquals(ChatterExceptionType.CHATTER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void create() {
    ChatterCreateRequest req = new ChatterCreateRequest("TESTER1", 101L);
    Chatter chatter = chatterCrudService.create(req);
    assertEquals(req.name(), chatter.getName());
    assertEquals(req.userId(), chatter.getUserId());
    assertNotNull(chatter.getCreatedAt());
  }

  @Test
  @Transactional
  void create_UniqueUserId_Exception() {
    ChatterCreateRequest req = new ChatterCreateRequest("TESTER1", 1L);

    ChatterException ex = assertThrowsExactly(ChatterException.class, () -> {
      chatterCrudService.create(req);
    });
    assertEquals(ChatterExceptionType.UNIQUE_USER_ID.name(), ex.getCode());
  }

  @Test
  @Transactional
  void update() {
    ChatterCreateRequest req = new ChatterCreateRequest("TESTER1", 111L);
    Chatter chatter = chatterCrudService.update(3L, req);
    assertEquals(req.name(), chatter.getName());
    assertEquals(req.userId(), chatter.getUserId());
    assertNotNull(chatter.getUpdatedAt());
    assertNotNull(chatter.getUpdatedBy());
  }

  @Test
  @Transactional
  void update_UniqueUserId_Exception() {
    ChatterCreateRequest req = new ChatterCreateRequest("TESTER1", 1L);

    ChatterException ex = assertThrowsExactly(ChatterException.class, () -> {
      chatterCrudService.update(3L, req);
    });
    assertEquals(ChatterExceptionType.UNIQUE_USER_ID.name(), ex.getCode());
  }

  @Test
  @Transactional
  void update_notFound_Exception() {
    ChatterCreateRequest req = new ChatterCreateRequest("TESTER1", 1L);
    ChatterException ex = assertThrowsExactly(ChatterException.class, () -> {
      chatterCrudService.update(notFoundId, req);
    });
    assertEquals(ChatterExceptionType.CHATTER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void delete() {
    Chatter chatter = chatterCrudService.delete(1L);
    assertNotNull(chatter.getDeletedAt());
    assertNotNull(chatter.getDeletedBy());
  }

  @Test
  @Transactional
  void delete_notFound_Exception() {
    ChatterException ex = assertThrowsExactly(ChatterException.class, () -> {
      chatterCrudService.delete(199L);
    });
    assertEquals(ChatterExceptionType.CHATTER_NOT_FOUND.name(), ex.getCode());
  }

}
