package com.arch.micro_service.chat_server.group.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.List;

import com.arch.micro_service.chat_server.group.application.service.impl.GroupCrudServiceImpl;
import com.arch.micro_service.chat_server.group.domain.entity.Group;
import com.arch.micro_service.chat_server.group.domain.exception.GroupException;
import com.arch.micro_service.chat_server.group.domain.exception.type.GroupExceptionType;
import com.arch.micro_service.chat_server.group.infrastructure.dto.request.GroupCreateRequest;
import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class GroupCrudServiceImplTest extends AbstractTestContainer {

  @Autowired
  private GroupCrudServiceImpl groupCrudServiceImpl;

  @Test
  @Transactional
  public void getAll() {
    List<Group> groups = groupCrudServiceImpl.getAll();
    assertEquals(10, groups.size());
  }

  @Test
  @Transactional
  void getbyId() {
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
  }

  // @Test
  // @Transactional
  // void create() {
  // var req = new GroupCreateRequest("TEST_GROUP", "this is an test ", null,
  // true);
  // var group = groupCrudServiceImpl.create(req);
  // assertEquals(1L, group.getId());
  // assertEquals("GROUP_1", group.getName());
  // assertEquals("THIS IS AN TEST GROUP 1", group.getDescription());
  // assertFalse(group.isAdminOnly());

  // }
}
