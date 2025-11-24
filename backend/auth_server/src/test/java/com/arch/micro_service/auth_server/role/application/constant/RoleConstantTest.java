package com.arch.micro_service.auth_server.role.application.constant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RoleConstantTest {

  @Test
  void test() {
    assertEquals("Role Created", RoleConstant.CREATE);
    assertEquals("Role Updated", RoleConstant.UPDATE);
    assertEquals("Role Deleted", RoleConstant.DELETE);
  }
}
