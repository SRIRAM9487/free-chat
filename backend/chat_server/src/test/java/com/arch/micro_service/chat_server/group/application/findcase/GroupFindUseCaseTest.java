package com.arch.micro_service.chat_server.group.application.findcase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import com.arch.micro_service.chat_server.group.application.usecase.GroupFindUseCase;
import com.arch.micro_service.chat_server.group.domain.entity.Group;
import com.arch.micro_service.chat_server.group.domain.exception.GroupException;
import com.arch.micro_service.chat_server.group.domain.exception.type.GroupExceptionType;
import com.arch.micro_service.chat_server.logger.CustomLogger;
import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

public class GroupFindUseCaseTest extends AbstractTestContainer {

  @Autowired
  private GroupFindUseCase groupFindUseCase;

  @MockitoBean
  private CustomLogger customLogger;

  @BeforeEach
  void setup() {
    doNothing().when(customLogger).success(anyString(), any(), any());
    doNothing().when(customLogger).failure(anyString(), any());
  }

  @Test
  @Transactional
  void findById() {
    Group group = groupFindUseCase.findById(1L);
    assertEquals(1L, group.getId());
    assertEquals("GROUP_1", group.getName());
    assertEquals("THIS IS AN TEST GROUP 1", group.getDescription());
    assertFalse(group.isAdminOnly());
  }

  @Test
  @Transactional
  void findById_notFound() {
    GroupException exception = assertThrowsExactly(GroupException.class, () -> {
      groupFindUseCase.findById(99L);
    });
    assertEquals(GroupExceptionType.GROUP_NOT_FOUND.name(), exception.getCode());

  }

}
