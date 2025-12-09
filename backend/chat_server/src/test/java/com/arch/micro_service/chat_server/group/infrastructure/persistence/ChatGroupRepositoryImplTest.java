package com.arch.micro_service.chat_server.group.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.domain.entity.ChatGroup;
import com.arch.micro_service.chat_server.chatgroup.domain.exception.ChatGroupException;
import com.arch.micro_service.chat_server.chatgroup.domain.exception.type.ChatGroupExceptionType;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.persistence.impl.ChatGroupRepositoryImpl;
import com.arch.micro_service.chat_server.logger.context.MetaContextHolder;
import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

public class ChatGroupRepositoryImplTest extends AbstractTestContainer {

  private final ChatGroupRepositoryImpl chatGroupRepositoryImpl;

  @MockitoBean
  private ApplicationEventPublisher publisher;

  @MockitoBean
  private MetaContextHolder metaContextHolder;

  @Autowired
  public ChatGroupRepositoryImplTest(ChatGroupRepositoryImpl chatGroupRepositoryImpl) {
    this.chatGroupRepositoryImpl = chatGroupRepositoryImpl;
  }

  @BeforeEach
  void setup() {
    doNothing().when(publisher).publishEvent(any());
  }

  @Test
  void getAll() {
    List<ChatGroup> groups = chatGroupRepositoryImpl.findAll();
    assertEquals(10, groups.size());
  }

  @Test
  void getById() {
    ChatGroup group = chatGroupRepositoryImpl.findById(1L);
    assertEquals(1L, group.getId());
    assertEquals("Group_Alpha", group.getName());
    assertEquals("General discussion group", group.getDescription());
  }

  @Test
  void getById_notFound() {
    ChatGroupException ex = assertThrowsExactly(ChatGroupException.class, () -> {
      chatGroupRepositoryImpl.findById(199L);
    });

    assertEquals(ChatGroupExceptionType.GROUP_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void create() {
    ChatGroup chat = new ChatGroup();
    chat.setName("HELLO");
    chat.setDescription("THIS IS AN TEST");
    chat.setCreatedBy("TESTER");
    ChatGroup savedChat = chatGroupRepositoryImpl.save(chat);
    assertEquals(chat.getName(), savedChat.getName());
    assertEquals(chat.getDescription(), savedChat.getDescription());
    assertEquals(chat.getCreatedBy(), savedChat.getCreatedBy());
    assertNotNull(savedChat.getId());
    assertNotNull(savedChat.getCreatedAt());

    List<ChatGroup> groups = chatGroupRepositoryImpl.findAll();
    assertEquals(11, groups.size());
  }

  @Test
  @Transactional
  void update() {
    ChatGroup chat = new ChatGroup();
    chat.setId(10L);
    chat.setName("HELLO");
    chat.setDescription("THIS IS AN TEST");
    chat.setUpdatedBy("TESTER");
    ChatGroup updatedChat = chatGroupRepositoryImpl.update(chat);
    assertEquals(chat.getId(), updatedChat.getId());
    assertEquals(chat.getName(), updatedChat.getName());
    assertEquals(chat.getDescription(), updatedChat.getDescription());
    assertEquals(chat.getUpdatedBy(), updatedChat.getUpdatedBy());
    assertEquals("SYSTEM", updatedChat.getCreatedBy());
  }

  @Test
  @Transactional
  void update_notFound() {
    ChatGroup chat = new ChatGroup();
    chat.setId(990L);
    chat.setName("HELLO");
    chat.setDescription("THIS IS AN TEST");
    chat.setUpdatedBy("TESTER");

    ChatGroupException ex = assertThrowsExactly(ChatGroupException.class, () -> {
      chatGroupRepositoryImpl.update(chat);
    });

    assertEquals(ChatGroupExceptionType.GROUP_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void delete() {
    ChatGroup chat = chatGroupRepositoryImpl.delete(1L);
    assertNotNull(chat.getDeletedAt());
    assertNotNull(chat.getDeletedBy());

  }
}
