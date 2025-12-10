package com.arch.micro_service.chat_server.group.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.domain.entity.AccessLevel;
import com.arch.micro_service.chat_server.chatgroup.domain.entity.GroupMember;
import com.arch.micro_service.chat_server.chatgroup.domain.exception.GroupMemberException;
import com.arch.micro_service.chat_server.chatgroup.domain.exception.type.GroupMemberExceptionType;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.persistence.GroupMemberRepository;
import com.arch.micro_service.chat_server.logger.context.MetaContext;
import com.arch.micro_service.chat_server.logger.context.MetaContextHolder;
import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

public class GroupMemberRepositoryImplTest extends AbstractTestContainer {

  @Autowired
  private GroupMemberRepository groupMemberRepository;

  private final Long notFoundId = 999L;

  @BeforeEach
  void setup() {
    MetaContextHolder.set(new MetaContext("Test user", "/test/path", "TEST PAT", 1000, "1.2.3.1", "FIRE FOX"));
  }

  @AfterEach
  void cleanup() {
    MetaContextHolder.clear();
  }

  @Test
  @Transactional
  void getById() {
    GroupMember member = groupMemberRepository.findById(1L);
    assertEquals(1, member.getId());
    assertEquals(1, member.getChatterId());
    assertEquals(AccessLevel.CREATOR, member.getAccessLevel());
    assertNotNull(member.isRestricted());
    assertNotNull(member.getCreatedAt());
    assertNotNull(member.getCreatedBy());
  }

  @Test
  @Transactional
  void getById_notFound_exception() {
    GroupMemberException ex = assertThrowsExactly(GroupMemberException.class, () -> {
      groupMemberRepository.findById(notFoundId);
    });
    assertEquals(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void create() {
    GroupMember groupMember = new GroupMember();
    groupMember.setChatterId(1L);
    groupMember.setGroupId(1L);
    groupMember.setAccessLevel(AccessLevel.CREATOR);
    groupMember.setRestricted(false);
    groupMember.setCreatedBy("TESTER");
    GroupMember saved = groupMemberRepository.save(groupMember);
    assertEquals(groupMember.getChatterId(), saved.getChatterId());
    assertEquals(groupMember.getGroupId(), saved.getGroupId());
    assertEquals(groupMember.isRestricted(), saved.isRestricted());
    assertNotNull(saved.getCreatedBy());
    assertNotNull(saved.getCreatedAt());
  }

  @Test
  @Transactional
  void create_invalidChatterId() {

    GroupMember groupMember = new GroupMember();
    groupMember.setChatterId(notFoundId);
    groupMember.setGroupId(1L);
    groupMember.setAccessLevel(AccessLevel.CREATOR);
    groupMember.setRestricted(false);
    groupMember.setCreatedBy("TESTER");

    GroupMemberException ex = assertThrowsExactly(GroupMemberException.class, () -> {
      groupMemberRepository.save(groupMember);
    });
    assertEquals(GroupMemberExceptionType.CHATTER_ID_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void create_invalidGroupId() {
    GroupMember groupMember = new GroupMember();
    groupMember.setAccessLevel(AccessLevel.CREATOR);
    groupMember.setRestricted(false);
    groupMember.setCreatedBy("TESTER");
    groupMember.setChatterId(1L);
    groupMember.setGroupId(notFoundId);
    GroupMemberException ex = assertThrowsExactly(GroupMemberException.class, () -> {
      groupMemberRepository.save(groupMember);
    });
    assertEquals(GroupMemberExceptionType.GROUP_ID_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void delete_byId() {
    MetaContextHolder.set(new MetaContext("Test user", "/test/path", "TEST PAT", 1000, "1.2.3.1", "FIRE FOX"));
    GroupMember member = groupMemberRepository.delete(1L);
    assertNotNull(member.getDeletedAt());
    assertNotNull(member.getDeletedBy());
    MetaContextHolder.clear();
  }

  @Test
  @Transactional
  void delete_byId_notFound() {
    GroupMemberException ex = assertThrowsExactly(GroupMemberException.class, () -> {
      groupMemberRepository.delete(notFoundId);
    });
    assertEquals(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void toggleRestrict() {
    GroupMember groupMember = groupMemberRepository.restrict(true, 1L);
    assertTrue(groupMember.isRestricted());
    assertNotNull(groupMember.getUpdatedAt());
    assertNotNull(groupMember.getUpdatedBy());
  }

  @Test
  @Transactional
  void toggleRestrict_notFound() {
    GroupMemberException ex = assertThrowsExactly(GroupMemberException.class, () -> {
      groupMemberRepository.restrict(true, notFoundId);
    });
    assertEquals(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void accessLevel() {
    GroupMember groupMember = groupMemberRepository.access(AccessLevel.CREATOR, 1L);
    assertEquals(AccessLevel.CREATOR, groupMember.getAccessLevel());
    assertNotNull(groupMember.getUpdatedAt());
    assertNotNull(groupMember.getUpdatedBy());
  }

  @Test
  @Transactional
  void accessLevel_notFound() {
    GroupMemberException ex = assertThrowsExactly(GroupMemberException.class, () -> {
      groupMemberRepository.access(AccessLevel.ADMIN, notFoundId);
    });
    assertEquals(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void findByGroupId() {
    Long groupId = 1L;
    List<GroupMember> members = groupMemberRepository.findByGroupId(groupId);
    for (var member : members) {
      assertEquals(groupId, member.getGroupId());
    }
  }

  @Test
  @Transactional
  void findByGroupId_notFound() {
    GroupMemberException ex = assertThrowsExactly(GroupMemberException.class, () -> {
      groupMemberRepository.findByGroupId(notFoundId);
    });
    assertEquals(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void deleteByGroupId() {
    Long groupId = 1L;
    List<GroupMember> members = groupMemberRepository.deleteByGroupId(groupId);
    for (var member : members) {
      assertEquals(groupId, member.getGroupId());
    }
  }

  @Test
  @Transactional
  void deleteByGroupId_notFound() {
    GroupMemberException ex = assertThrowsExactly(GroupMemberException.class, () -> {
      groupMemberRepository.deleteByGroupId(notFoundId);
    });
    assertEquals(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name(), ex.getCode());
  }

}
