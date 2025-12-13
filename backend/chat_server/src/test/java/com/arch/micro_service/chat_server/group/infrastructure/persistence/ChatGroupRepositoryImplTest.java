package com.arch.micro_service.chat_server.group.infrastructure.persistence;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.domain.entity.ChatGroup;
import com.arch.micro_service.chat_server.chatgroup.domain.exception.ChatGroupException;
import com.arch.micro_service.chat_server.chatgroup.domain.exception.type.ChatGroupExceptionType;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.ChatMessage;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.persistence.impl.ChatGroupRepositoryImpl;
import com.arch.micro_service.chat_server.logger.context.MetaContext;
import com.arch.micro_service.chat_server.logger.context.MetaContextHolder;
import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

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

  @Test
  void getAll() {
    List<ChatGroup> groups = chatGroupRepositoryImpl.findAll();
    assertFalse(groups.isEmpty());
  }

  @Test
  void getById() {
    ChatGroup group = chatGroupRepositoryImpl.findById(1L);
    assertEquals(1L, group.getId());
    assertNotNull(group.getName());
    assertNotNull(group.getDescription());
    assertNotNull(group.getCreatedAt());
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
    assertFalse(groups.isEmpty());

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
    MetaContextHolder.set(new MetaContext("Test user", "/test/path", "TEST PAT", 1000, "1.2.3.1", "FIRE FOX"));
    ChatGroup chat = chatGroupRepositoryImpl.delete(1L);
    assertNotNull(chat.getDeletedAt());
    assertNotNull(chat.getDeletedBy());
    MetaContextHolder.clear();
  }

  @Test
  @Transactional
  void delete_notFound() {
    MetaContextHolder.set(new MetaContext("Test user", "/test/path", "TEST PAT", 1000, "1.2.3.1", "FIRE FOX"));
    var ex = assertThrowsExactly(ChatGroupException.class, () -> {
      chatGroupRepositoryImpl.delete(999L);
    });
    assertEquals(ChatGroupExceptionType.GROUP_NOT_FOUND.name(), ex.getCode());
    MetaContextHolder.clear();
  }

  @Test
  @Transactional
  void findMessageByGroupId() {
    List<ChatMessage> messages = chatGroupRepositoryImpl.findMessageByGroupId(1L);
    assertFalse(messages.isEmpty());
  }

}
