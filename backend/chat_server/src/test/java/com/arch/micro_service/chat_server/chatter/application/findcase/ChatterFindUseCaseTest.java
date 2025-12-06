package com.arch.micro_service.chat_server.chatter.application.findcase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import com.arch.micro_service.chat_server.chatter.application.usecase.ChatterFindUseCase;
import com.arch.micro_service.chat_server.chatter.domain.exception.ChatterException;
import com.arch.micro_service.chat_server.chatter.domain.exception.type.ChatterExceptionType;
import com.arch.micro_service.chat_server.logger.CustomLogger;
import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

public class ChatterFindUseCaseTest extends AbstractTestContainer {

  @Autowired
  private ChatterFindUseCase chatterFindUseCase;

  @MockitoBean
  private CustomLogger customLogger;

  @Test
  @Transactional
  void findById() {
    doNothing().when(customLogger).success(anyString(), any(), any());
    var chatter = chatterFindUseCase.findById(1L);
    assertEquals(1L, chatter.getId());
    assertEquals("CHATTER_1", chatter.getName());
    assertFalse(chatter.isBanned());
  }

  @Test
  @Transactional
  void findById_notFoundexception() {
    doNothing().when(customLogger).failure(anyString(), any());
    ChatterException chatterException = assertThrowsExactly(ChatterException.class, () -> {
      chatterFindUseCase.findById(9999999L);
    });
    assertEquals(ChatterExceptionType.CHATTER_NOT_FOUND.name(), chatterException.getCode(), "Chatter not found");
  }

}
