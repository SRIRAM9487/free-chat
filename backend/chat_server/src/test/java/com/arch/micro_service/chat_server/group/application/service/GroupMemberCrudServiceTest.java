package com.arch.micro_service.chat_server.group.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.application.service.GroupMemberService;
import com.arch.micro_service.chat_server.chatgroup.domain.entity.AccessLevel;
import com.arch.micro_service.chat_server.chatgroup.domain.entity.GroupMember;
import com.arch.micro_service.chat_server.chatgroup.domain.exception.GroupMemberException;
import com.arch.micro_service.chat_server.chatgroup.domain.exception.type.GroupMemberExceptionType;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.request.GroupMemberCreateRequest;
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

public class GroupMemberCrudServiceTest extends AbstractTestContainer {

  @Autowired
  private GroupMemberService groupMemberService;

  @MockitoBean
  private ApplicationEventPublisher applicationEventPublisher;

  private final Long notFoundId = 9999L;

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
    GroupMember member = groupMemberService.findById(1L);
    assertEquals(1, member.getId());
    assertEquals(1, member.getChatterId());
    assertEquals(AccessLevel.CREATOR, member.getAccessLevel());
    assertNotNull(member.isRestricted());
    assertNotNull(member.getCreatedAt());
    assertNotNull(member.getCreatedBy());
  }

  @Test
  @Transactional
  void findById_notFound() {
    GroupMemberException ex = assertThrowsExactly(GroupMemberException.class, () -> {
      groupMemberService.findById(notFoundId);
    });
    assertEquals(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void create() {
    GroupMemberCreateRequest request = new GroupMemberCreateRequest(1L, 2L, AccessLevel.CREATOR.name(), true);
    GroupMember member = groupMemberService.create(request);
    assertNotNull(member.getGroupId());
    assertEquals(request.groupId(), member.getGroupId());
    assertEquals(request.chatterId(), member.getChatterId());
    assertEquals(request.accessLevel(), member.getAccessLevel().name());
    assertEquals(request.restricted(), member.isRestricted());
    assertNotNull(member.getCreatedAt());
    assertNotNull(member.getCreatedBy());
  }

  @Test
  @Transactional
  void create_invalidChatterId() {
    GroupMemberCreateRequest request = new GroupMemberCreateRequest(notFoundId, 1L, AccessLevel.CREATOR.name(), true);
    GroupMemberException ex = assertThrowsExactly(GroupMemberException.class, () -> {
      groupMemberService.create(request);
    });
    assertEquals(GroupMemberExceptionType.CHATTER_ID_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void create_invalidGroupId() {
    GroupMemberCreateRequest request = new GroupMemberCreateRequest(1L, notFoundId, AccessLevel.CREATOR.name(), true);
    GroupMemberException ex = assertThrowsExactly(GroupMemberException.class, () -> {
      groupMemberService.create(request);
    });
    assertEquals(GroupMemberExceptionType.GROUP_ID_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void delete_byId() {
    GroupMember member = groupMemberService.delete(1L);
    assertNotNull(member.getDeletedAt());
    assertNotNull(member.getDeletedBy());
  }

  @Test
  @Transactional
  void delete_byId_notFound() {
    GroupMemberException ex = assertThrowsExactly(GroupMemberException.class, () -> {
      groupMemberService.delete(notFoundId);
    });
    assertEquals(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void toggleRestrict() {
    GroupMember groupMember = groupMemberService.restrict(true, 1L);
    assertTrue(groupMember.isRestricted());
    assertNotNull(groupMember.getUpdatedAt());
    assertNotNull(groupMember.getUpdatedBy());
  }

  @Test
  @Transactional
  void toggleRestrict_notFound() {
    GroupMemberException ex = assertThrowsExactly(GroupMemberException.class, () -> {
      groupMemberService.restrict(true, notFoundId);
    });
    assertEquals(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void accessLevel() {
    GroupMember groupMember = groupMemberService.access(AccessLevel.CREATOR.name(), 1L);
    assertEquals(AccessLevel.CREATOR, groupMember.getAccessLevel());
    assertNotNull(groupMember.getUpdatedAt());
    assertNotNull(groupMember.getUpdatedBy());
  }

  @Test
  @Transactional
  void accessLevel_notFound() {
    GroupMemberException ex = assertThrowsExactly(GroupMemberException.class, () -> {
      groupMemberService.access(AccessLevel.ADMIN.name(), notFoundId);
    });
    assertEquals(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void findByGroupId() {
    Long groupId = 1L;
    List<GroupMember> members = groupMemberService.findByGroupId(groupId);
    for (var member : members) {
      assertEquals(groupId, member.getGroupId());
    }
  }

  @Test
  @Transactional
  void findByGroupId_notFound() {
    GroupMemberException ex = assertThrowsExactly(GroupMemberException.class, () -> {
      groupMemberService.findByGroupId(notFoundId);
    });
    assertEquals(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void deleteByGroupId() {
    Long groupId = 1L;
    List<GroupMember> members = groupMemberService.deleteByGroupId(groupId);
    for (var member : members) {
      assertEquals(groupId, member.getGroupId());
    }
  }

  @Test
  @Transactional
  void deleteByGroupId_notFound() {
    GroupMemberException ex = assertThrowsExactly(GroupMemberException.class, () -> {
      groupMemberService.deleteByGroupId(notFoundId);
    });
    assertEquals(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name(), ex.getCode());
  }

}
