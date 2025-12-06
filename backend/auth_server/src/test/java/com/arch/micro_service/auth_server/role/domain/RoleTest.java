package com.arch.micro_service.auth_server.role.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.testcontainer.AbstractTestContainer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

public class RoleTest extends AbstractTestContainer {

  private Role role;

  @BeforeEach
  void setup() {
    role = Role
        .builder()
        .title("Test role")
        .active(true)
        .build();
  }

  @Test
  @Transactional
  void toggleRoleActiveStatus() {
    assertTrue(role.isActive(), "Role is active");
    role.toggleActive();
    assertFalse(role.isActive(), "Role is not active");
  }

}
