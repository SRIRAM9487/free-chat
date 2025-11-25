package com.arch.micro_service.auth_server.role.persistence.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.mapper.PermissionMapper;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request.PermissionCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.response.PermissionDetailResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PermissionMapperTest {

  private PermissionMapper permissionMapper;

  @BeforeEach
  void setup() {
    permissionMapper = new PermissionMapper();
  }

  @Test
  void fromPermissionToPermissionDetailsResponse() {
    Permission permission = Permission
        .builder()
        .title("TEST_PERMISSION")
        .active(true)
        .build();
    permission.setId(1L);
    PermissionDetailResponse res = permissionMapper.fromPermission(permission);
    assertEquals(permission.getId(), res.id());
    assertEquals(permission.getTitle(), res.title());
    assertEquals(permission.isActive(), res.active());
  }

  @Test
  void updateThePermission() {
    Permission permission = Permission
        .builder()
        .title("TEST_PERMISSION")
        .active(true)
        .build();
    PermissionCreateRequest req1 = new PermissionCreateRequest("UPDATED_TEST_PERMISSION", false);
    permissionMapper.update(permission, req1);
    assertEquals(permission.getTitle(), req1.title());
    assertEquals(permission.isActive(), req1.active());
  }

  @Test
  void fromPermissionCreateRequestToPermission() {
    PermissionCreateRequest res1 = new PermissionCreateRequest("TEST_PERMISSION", true);
    Permission permission = permissionMapper.toPermission(res1);
    assertEquals(res1.title(), permission.getTitle());
    assertEquals(res1.active(), permission.isActive());
  }
}
