package com.arch.micro_service.auth_server.role.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import com.arch.micro_service.auth_server.log.CustomLogger;
import com.arch.micro_service.auth_server.role.application.service.permission.PermissionCrudService;
import com.arch.micro_service.auth_server.role.application.usecase.permission.PermissionFindUseCase;
import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.domain.exception.PermissionException;
import com.arch.micro_service.auth_server.role.domain.exception.type.PermissionExceptionType;
import com.arch.micro_service.auth_server.testcontainer.AbstractTestContainer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

public class PermissionFindUseCaseTest extends AbstractTestContainer {

  @Autowired
  private PermissionFindUseCase permissionFindUseCase;

  @Autowired
  private PermissionCrudService permissionCrudService;

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
    Permission permission = permissionFindUseCase.findById("1");
    assertEquals("PERMISSION_CREATE", permission.getTitle());
    assertTrue(permission.isActive());
  }

  @Test
  @Transactional
  void getByIdNotFound() {

    PermissionException exception = assertThrowsExactly(PermissionException.class,
        () -> permissionFindUseCase.findById("588"));

    assertEquals(PermissionExceptionType.PERMISSION_NOT_FOUND.name(), exception.getCode());

    permissionCrudService.delete("1");

    exception = assertThrowsExactly(PermissionException.class,
        () -> permissionFindUseCase.findById("1"));

    assertEquals(PermissionExceptionType.PERMISSION_NOT_FOUND.name(), exception.getCode());
  }
}
