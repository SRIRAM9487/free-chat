package com.arch.micro_service.auth_server.role.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.arch.micro_service.auth_server.role.application.service.permission.PermissionCrudService;
import com.arch.micro_service.auth_server.role.application.usecase.permission.PermissionFindUseCase;
import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.domain.exception.PermissionException;
import com.arch.micro_service.auth_server.role.domain.exception.type.PermissionExceptionType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class PermissionFindUseCaseTest {

  @Autowired
  private PermissionFindUseCase permissionFindUseCase;

  @Autowired
  private PermissionCrudService permissionCrudService;

  @Test
  void findById() {
    Permission permission = permissionFindUseCase.findById("1");
    assertEquals(permission.getTitle(), "ROLE_CREATE");
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
