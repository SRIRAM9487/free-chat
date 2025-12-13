package com.arch.micro_service.chat_server.chatter.infrastructure.persistence;

import java.util.List;

import com.arch.micro_service.chat_server.chatter.domain.entity.Chatter;
import com.arch.micro_service.chat_server.chatter.domain.exception.ChatterException;
import com.arch.micro_service.chat_server.chatter.domain.exception.type.ChatterExceptionType;
import com.arch.micro_service.chat_server.logger.context.MetaContext;
import com.arch.micro_service.chat_server.logger.context.MetaContextHolder;
import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

public class ChatterRepositoryTest extends AbstractTestContainer {

  @Autowired
  private ChatterRepository chatterRepository;

  @Test
  @Transactional
  void getAll() {
    List<Chatter> chatters = chatterRepository.findAll();
    assertFalse( chatters.isEmpty());
  }

  @Test
  @Transactional
  void getById() {
    Chatter chatter = chatterRepository.findById(1L);
    assertEquals(1L, chatter.getId());
    assertEquals(1L, chatter.getUserId());
    assertNotNull(chatter.getName());
  }

  @Test
  @Transactional
  void getById_notFound() {
    ChatterException ex = assertThrowsExactly(ChatterException.class, () -> {
      chatterRepository.findById(1000L);
    });
    assertEquals(ChatterExceptionType.CHATTER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void create() {
    Chatter chatter = new Chatter();
    chatter.setName("Tester 1");
    chatter.setUserId(251L);
    chatter.setCreatedBy("TESTER1");
    Chatter saved = chatterRepository.save(chatter);
    assertEquals(chatter.getName(), saved.getName());
    assertEquals(chatter.getCreatedBy(), saved.getCreatedBy());
    assertNotNull(saved.getCreatedAt());
    assertNull(saved.getUpdatedBy());
    assertNull(saved.getUpdatedAt());
    assertNull(saved.getDeletedBy());
    assertNull(saved.getDeletedAt());
  }

  @Test
  @Transactional
  void create_UserIdUniqueException() {
    Chatter chatter = new Chatter();
    chatter.setName("Tester 1");
    chatter.setUserId(1L);
    chatter.setCreatedBy("TESTER1");
    ChatterException ex = assertThrowsExactly(ChatterException.class, () -> {
      chatterRepository.save(chatter);
    });
    assertEquals(ChatterExceptionType.UNIQUE_USER_ID.name(), ex.getCode());
  }

  @Test
  @Transactional
  void update() {
    Chatter chatter = new Chatter();
    chatter.setId(1L);
    chatter.setName("Tester 1");
    chatter.setUserId(1L);
    chatter.setUpdatedBy("TESTER1");
    Chatter saved = chatterRepository.update(chatter);
    assertEquals(chatter.getId(), saved.getId());
    assertEquals(chatter.getName(), saved.getName());
    assertNotNull(saved.getCreatedAt());
    assertNotNull(saved.getUpdatedBy());
    assertNotNull(saved.getUpdatedAt());
    assertNull(saved.getDeletedBy());
    assertNull(saved.getDeletedAt());

  }

  @Test
  @Transactional
  void update_UserIdUnique_Exception() {
    Chatter chatter = new Chatter();
    chatter.setId(1L);
    chatter.setName("Tester 1");
    chatter.setUserId(4L);
    chatter.setCreatedBy("TESTER1");
    ChatterException ex = assertThrowsExactly(ChatterException.class, () -> {
      chatterRepository.update(chatter);
    });
    assertEquals(ChatterExceptionType.UNIQUE_USER_ID.name(), ex.getCode());
  }

  @Test
  @Transactional
  void update_notFound_Exception() {
    Chatter chatter = new Chatter();
    chatter.setId(9999L);
    chatter.setName("Tester 1");
    chatter.setUserId(1L);
    chatter.setCreatedBy("TESTER1");
    ChatterException ex = assertThrowsExactly(ChatterException.class, () -> {
      chatterRepository.update(chatter);
    });
    assertEquals(ChatterExceptionType.CHATTER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void delete() {
    MetaContextHolder.set(new MetaContext("Test user", "/test/path", "TEST PAT", 1000, "1.2.3.1", "FIRE FOX"));
    Chatter chatter = chatterRepository.delete(1L);
    assertNotNull(chatter.getDeletedAt());
    assertNotNull(chatter.getDeletedBy());
    MetaContextHolder.clear();
  }

  @Test
  @Transactional
  void delete_notFound_Exception() {
    MetaContextHolder.set(new MetaContext("Test user", "/test/path", "TEST PAT", 1000, "1.2.3.1", "FIRE FOX"));
    ChatterException ex = assertThrowsExactly(ChatterException.class, () -> {
      chatterRepository.delete(999L);
    });
    assertEquals(ChatterExceptionType.CHATTER_NOT_FOUND.name(), ex.getCode());
    MetaContextHolder.clear();
  }
}
