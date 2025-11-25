package com.arch.micro_service.auth_server.user.application.constant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserCrudConstantTest {

  @Test
  void test() {
    assertEquals("User Created", UserCrudConstant.CREATE);
    assertEquals("User Updated", UserCrudConstant.UPDATE);
    assertEquals("User Deleted", UserCrudConstant.DELETE);
  }

}
