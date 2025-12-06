package com.arch.micro_service.chat_server.group.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import java.util.List;

import com.arch.micro_service.chat_server.chatter.domain.exception.ChatterException;
import com.arch.micro_service.chat_server.chatter.domain.exception.type.ChatterExceptionType;
import com.arch.micro_service.chat_server.group.application.service.impl.GroupCrudServiceImpl;
import com.arch.micro_service.chat_server.group.domain.entity.AccessLevel;
import com.arch.micro_service.chat_server.group.domain.entity.Group;
import com.arch.micro_service.chat_server.group.domain.exception.GroupException;
import com.arch.micro_service.chat_server.group.domain.exception.type.GroupExceptionType;
import com.arch.micro_service.chat_server.group.infrastructure.dto.request.GroupCreateRequest;
import com.arch.micro_service.chat_server.group.infrastructure.dto.request.GroupMemberCreateRequest;
import com.arch.micro_service.chat_server.logger.CustomLogger;
import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

public class GroupCrudServiceImplTest extends AbstractTestContainer {

  @Autowired
  private GroupCrudServiceImpl groupCrudServiceImpl;

  @MockitoBean
  private CustomLogger customLogger;

  @BeforeEach
  void setup() {
    doNothing().when(customLogger).success(anyString(), any(), any());
    doNothing().when(customLogger).failure(anyString(), any());
  }

  @Test
  @Transactional
  public void getAll() {
    List<Group> groups = groupCrudServiceImpl.getAll();
    assertEquals(10, groups.size());
  }

  @Test
  @Transactional
  void getById() {
    Group group = groupCrudServiceImpl.get("1");
    assertEquals(1L, group.getId());
    assertEquals("GROUP_1", group.getName());
    assertEquals("THIS IS AN TEST GROUP 1", group.getDescription());
    assertFalse(group.isAdminOnly());
  }

  @Test
  @Transactional
  void getById_notFound() {
    GroupException exception = assertThrowsExactly(GroupException.class, () -> {
      groupCrudServiceImpl.get("88");
    });
    assertEquals(GroupExceptionType.GROUP_NOT_FOUND.name(), exception.getCode());

    groupCrudServiceImpl.delete("1");
    exception = assertThrowsExactly(GroupException.class, () -> {
      groupCrudServiceImpl.get("1");
    });
    assertEquals(GroupExceptionType.GROUP_NOT_FOUND.name(), exception.getCode());
  }

  @Test
  @Transactional
  void create() {
    var groupMemberIds = List.of(
        new GroupMemberCreateRequest(1L, AccessLevel.ADMIN.name()),
        new GroupMemberCreateRequest(2L, AccessLevel.ADMIN.name()),
        new GroupMemberCreateRequest(3L, AccessLevel.MEMBER.name()));
    var req = new GroupCreateRequest("TEST_GROUP", "this is an test", groupMemberIds, true);
    var group = groupCrudServiceImpl.create(req);
    assertEquals(11L, group.getId());
    assertEquals("TEST_GROUP", group.getName());
    assertEquals("this is an test", group.getDescription());
    assertTrue(group.isAdminOnly());
    assertFalse(group.getGroupMembers().isEmpty());
  }

  @Test
  @Transactional
  void create_chatterFound() {
    var groupMemberIds = List.of(
        new GroupMemberCreateRequest(991L, AccessLevel.ADMIN.name()),
        new GroupMemberCreateRequest(2L, AccessLevel.ADMIN.name()),
        new GroupMemberCreateRequest(3L, AccessLevel.MEMBER.name()));
    var req = new GroupCreateRequest("TEST_GROUP", "this is an test", groupMemberIds, true);
    ChatterException exception = assertThrowsExactly(ChatterException.class, () -> {
      groupCrudServiceImpl.create(req);
    });
    assertEquals(ChatterExceptionType.CHATTER_NOT_FOUND.name(), exception.getCode());
  }

  @Test
  @Transactional
  void update_success() {
    var groupMemberIds = List.of(
        new GroupMemberCreateRequest(1L, AccessLevel.ADMIN.name()),
        new GroupMemberCreateRequest(2L, AccessLevel.ADMIN.name()),
        new GroupMemberCreateRequest(3L, AccessLevel.MEMBER.name()));
    var req = new GroupCreateRequest("UPDATED_TEST_GROUP", "this is an updated test", groupMemberIds, false);
    Group group = groupCrudServiceImpl.update("1", req);
    assertEquals(1L, group.getId());
    assertEquals("UPDATED_TEST_GROUP", group.getName());
    assertEquals("this is an updated test", group.getDescription());
    assertFalse(group.isAdminOnly());
    assertFalse(group.getGroupMembers().isEmpty());
  }

  @Test
  @Transactional
  void update_chatterFound() {
    var groupMemberIds = List.of(
        new GroupMemberCreateRequest(991L, AccessLevel.ADMIN.name()),
        new GroupMemberCreateRequest(2L, AccessLevel.ADMIN.name()),
        new GroupMemberCreateRequest(3L, AccessLevel.MEMBER.name()));
    var req = new GroupCreateRequest("TEST_GROUP", "this is an test", groupMemberIds, true);
    ChatterException exception = assertThrowsExactly(ChatterException.class, () -> {
      groupCrudServiceImpl.update("1", req);
    });
    assertEquals(ChatterExceptionType.CHATTER_NOT_FOUND.name(), exception.getCode());
  }

  @Test
  @Transactional
  void delete_success() {
    int groups = groupCrudServiceImpl.getAll().size();
    var group = groupCrudServiceImpl.delete("1");
    assertEquals(groups - 1, groupCrudServiceImpl.getAll().size());
    assertNotNull(group.getDeletedAt());
  }

  @Test
  @Transactional
  void delete_notFound() {
    GroupException exception = assertThrowsExactly(GroupException.class, () -> {
      groupCrudServiceImpl.delete("99");
    });
    assertEquals(GroupExceptionType.GROUP_NOT_FOUND.name(), exception.getCode());
  }
}
