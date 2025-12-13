package com.arch.micro_service.chat_server.group.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import com.arch.micro_service.chat_server.chatgroup.application.service.ChatGroupCrudService;
import com.arch.micro_service.chat_server.chatgroup.domain.entity.ChatGroup;
import com.arch.micro_service.chat_server.chatgroup.domain.exception.ChatGroupException;
import com.arch.micro_service.chat_server.chatgroup.domain.exception.type.ChatGroupExceptionType;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.request.ChatGroupCreateRequest;
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

public class ChatGroupCrudServiceTest extends AbstractTestContainer {

  @Autowired
  private ChatGroupCrudService chatGroupCrudService;

  @MockitoBean
  private ApplicationEventPublisher applicationEventPublisher;

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
  void findById() {
    ChatGroup group = chatGroupCrudService.findById(1L);
    assertEquals(1L, group.getId());
    assertNotNull( group.getName());
      assertNotNull( group.getDescription());
  }

  @Test
  @Transactional
  void save() {
    ChatGroupCreateRequest req = new ChatGroupCreateRequest("TEST GROUP", "THIS IS AN TEST GROUP");
    ChatGroup group = chatGroupCrudService.create(req);
    assertNotNull( group.getId());
    assertEquals(req.name(), group.getName());
    assertEquals(req.description(), group.getDescription());
    assertNotNull(group.getCreatedBy());
    assertNotNull(group.getCreatedAt());
  }

  @Test
  @Transactional
  void update() {
    ChatGroupCreateRequest req = new ChatGroupCreateRequest("TEST GROUP", "THIS IS AN TEST GROUP");
    ChatGroup group = chatGroupCrudService.update(1L, req);
    assertEquals(1L, group.getId());
    assertEquals(req.name(), group.getName());
    assertEquals(req.description(), group.getDescription());
    assertNotNull(group.getCreatedBy());
    assertNotNull(group.getCreatedAt());
    assertNotNull(group.getUpdatedBy());
    assertNotNull(group.getUpdatedAt());
  }

  @Test
  @Transactional
  void update_notFound() {
    ChatGroupCreateRequest req = new ChatGroupCreateRequest("TEST GROUP", "THIS IS AN TEST GROUP");
    ChatGroupException ex = assertThrowsExactly(ChatGroupException.class, () -> {
      chatGroupCrudService.update(990L, req);
    });
    assertEquals(ChatGroupExceptionType.GROUP_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void delete() {
    ChatGroup group = chatGroupCrudService.delete(1L);
    assertEquals(1L, group.getId());
    assertNotNull(group.getDeletedAt());
    assertNotNull(group.getDeletedBy());
  }

  @Test
  @Transactional
  void delete_notFound() {
    ChatGroupException ex = assertThrowsExactly(ChatGroupException.class, () -> {
      chatGroupCrudService.delete(9999L);
    });
    assertEquals(ChatGroupExceptionType.GROUP_NOT_FOUND.name(), ex.getCode());
  }

}
