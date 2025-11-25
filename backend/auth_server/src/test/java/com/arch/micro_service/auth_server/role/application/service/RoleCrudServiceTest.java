package com.arch.micro_service.auth_server.role.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.arch.micro_service.auth_server.role.application.service.permission.PermissionCrudService;
import com.arch.micro_service.auth_server.role.application.service.role.RoleCrudService;
import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.domain.exception.RoleException;
import com.arch.micro_service.auth_server.role.domain.exception.type.RoleExceptionType;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RoleCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RolePermissionCreateRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class RoleCrudServiceTest {

  @Autowired
  private RoleCrudService roleCrudService;
  @Autowired
  private PermissionCrudService permissionCrudService;

  @Test
  void getAll() {
    List<Role> roles = roleCrudService.getAll();
    assertEquals(5, roles.size());
  }

  @Test
  void getById() {
    Role role = roleCrudService.get("1");
    assertEquals("ADMIN", role.getTitle());
    assertTrue(role.isActive());
  }

  @Test
  void getByIdNotFound() {
    RoleException exception = assertThrowsExactly(RoleException.class,
        () -> roleCrudService.get("588"));
    assertEquals(RoleExceptionType.ROLE_NOT_FOUND.name(), exception.getCode());
  }

  @Test
  void createRole() {
    List<Permission> permissions = permissionCrudService.getAll();
    List<RolePermissionCreateRequest> rpreq = List.of(
        new RolePermissionCreateRequest(permissions.get(0).getId().toString(), true),
        new RolePermissionCreateRequest(permissions.get(1).getId().toString(), true),
        new RolePermissionCreateRequest(permissions.get(2).getId().toString(), true));
    RoleCreateRequest req = new RoleCreateRequest("TESTER", true, rpreq);

    Role role = roleCrudService.create(req);

    assertEquals(req.title(), role.getTitle());
    assertEquals(req.active(), role.isActive());
    assertNotNull(role.getRolePermissions());

    List<String> savedPermissionIds = role.getRolePermissions()
        .stream()
        .map(rp -> rp.getPermission().getId().toString())
        .toList();

    List<String> requestIds = rpreq.stream()
        .map(RolePermissionCreateRequest::permissionId)
        .toList();

    assertEquals(requestIds.size(), savedPermissionIds.size());
    assertTrue(savedPermissionIds.containsAll(requestIds));
  }

  @Test
  void updateRole() {

    List<Permission> permissions = permissionCrudService.getAll();

    List<RolePermissionCreateRequest> rpreq = List.of(
        new RolePermissionCreateRequest(permissions.get(0).getId().toString(), true),
        new RolePermissionCreateRequest(permissions.get(1).getId().toString(), true),
        new RolePermissionCreateRequest(permissions.get(2).getId().toString(), true));
    RoleCreateRequest req = new RoleCreateRequest("TESTER", true, rpreq);

    List<RolePermissionCreateRequest> updateRolePermissionReq = List.of(
        new RolePermissionCreateRequest(permissions.get(0).getId().toString(), false),
        new RolePermissionCreateRequest(permissions.get(2).getId().toString(), true));
    RoleCreateRequest req2 = new RoleCreateRequest("UPDATED TEST", false, updateRolePermissionReq);

    Role role = roleCrudService.create(req);

    Role updatedRole = roleCrudService.update(role.getId().toString(), req2);

    assertEquals(req2.title(), updatedRole.getTitle());
    assertEquals(req2.active(), updatedRole.isActive());

    List<String> savedPermissionIds = updatedRole.getRolePermissions()
        .stream()
        .map(rp -> rp.getPermission().getId().toString())
        .toList();

    List<String> requestIds = updateRolePermissionReq.stream()
        .map(RolePermissionCreateRequest::permissionId)
        .toList();

    assertEquals(requestIds.size(), savedPermissionIds.size());
    assertTrue(savedPermissionIds.containsAll(requestIds));
  }

  @Test
  void deleteRole() {

    List<Role> roles = roleCrudService.getAll();

    Role deletedRole = roleCrudService.delete(roles.getFirst().getId().toString());

    assertNotNull(deletedRole.isDeleted());
    assertEquals(roles.size() - 1, roleCrudService.getAll().size());

  }
}
