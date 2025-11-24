package com.arch.micro_service.auth_server.role.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.List;

import com.arch.micro_service.auth_server.role.application.service.permission.PermissionCrudService;
import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.domain.exception.PermissionException;
import com.arch.micro_service.auth_server.role.domain.exception.type.PermissionExceptionType;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request.PermissionCreateRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class PermissionCrudServiceTest {

  @Autowired
  private PermissionCrudService permissionCrudService;

  @Test
  void getAll() {
    List<Permission> permissions = permissionCrudService.getAll();
    assertEquals(12, permissions.size());
  }

  @Test
  void getById() {
    Permission permission = permissionCrudService.get("1");
    assertEquals("ROLE_CREATE", permission.getTitle());
    assertTrue(permission.isActive());
  }

  @Test
  @Transactional
  void getByIdNotFound() {
    PermissionException exception = assertThrowsExactly(PermissionException.class,
        () -> permissionCrudService.get("588"));
    assertEquals(PermissionExceptionType.PERMISSION_NOT_FOUND.name(), exception.getCode());
  }

  @Test
  @Transactional
  void createNewPermission() {
    PermissionCreateRequest testCreate = new PermissionCreateRequest("TEST_CREATE", true);
    Permission createdTestPermission = permissionCrudService.create(testCreate);
    Permission testPermission = permissionCrudService.get(createdTestPermission.getId().toString());
    assertEquals("TEST_CREATE", testPermission.getTitle());
    assertTrue(testPermission.isActive());
    assertNull(testPermission.getDeletedAt());
    assertNull(testPermission.getDeletedBy());
  }

  @Test
  @Transactional
  void createNewPermissionUniqueTitleException() {
    PermissionCreateRequest testCreate = new PermissionCreateRequest("TEST_CREATE", true);
    permissionCrudService.create(testCreate);
    assertThrowsExactly(DataIntegrityViolationException.class,
        () -> permissionCrudService.create(testCreate), "Title must be unique");
  }

  @Test
  @Transactional
  void updatePermission() {
    PermissionCreateRequest testCreate = new PermissionCreateRequest("TEST_CREATE", true);
    PermissionCreateRequest updateCreate = new PermissionCreateRequest("UPDATE_TEST_CREATE", false);
    Permission createdPermission = permissionCrudService.create(testCreate);
    Permission updatedPermission = permissionCrudService.update(createdPermission.getId().toString(), updateCreate);
    assertEquals(createdPermission.getId(), updatedPermission.getId());
    assertEquals(updateCreate.title(), updatedPermission.getTitle());
    assertFalse(updatedPermission.isActive());
  }

  @Test
  @Transactional
  void deletePermission() {
    Collection<Permission> permissions = permissionCrudService.getAll();
    Permission permission = permissionCrudService.delete("1");
    assertNotNull(permission.getDeletedAt());
    assertEquals(permissionCrudService.getAll().size(), permissions.size() - 1);
  }
}
