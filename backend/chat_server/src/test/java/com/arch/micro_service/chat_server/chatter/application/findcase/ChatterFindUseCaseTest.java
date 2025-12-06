package com.arch.micro_service.chat_server.chatter.application.findcase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import com.arch.micro_service.chat_server.chatter.application.usecase.ChatterFindUseCase;
import com.arch.micro_service.chat_server.chatter.domain.exception.ChatterException;
import com.arch.micro_service.chat_server.chatter.domain.exception.type.ChatterExceptionType;
import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ChatterFindUseCaseTest extends AbstractTestContainer {

  @Autowired
  private ChatterFindUseCase chatterFindUseCase;

  @Test
  @Transactional
  void findById() {
    var chatter = chatterFindUseCase.findById(1L);
    assertEquals(1L, chatter.getId());
    assertEquals("CHATTER_1", chatter.getName());
    assertFalse(chatter.isBanned());
  }

  @Test
  @Transactional
  void findById_notFoundexception() {
    ChatterException chatterException = assertThrowsExactly(ChatterException.class, () -> {
      chatterFindUseCase.findById(9999999L);
    });
    assertEquals(ChatterExceptionType.CHATTER_NOT_FOUND.name(), chatterException.getCode(), "Chatter not found");
  }

}
