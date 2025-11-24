package com.arch.micro_service.auth_server.role.application.constant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PermissionConstantTest {

  @Test
  void test() {
    assertEquals("Permission Created", PermissionConstant.CREATE);
    assertEquals("Permission Updated", PermissionConstant.UPDATE);
    assertEquals("Permission Deleted", PermissionConstant.DELETE);
  }

}
