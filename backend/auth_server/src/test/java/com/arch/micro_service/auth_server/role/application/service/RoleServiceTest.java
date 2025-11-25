package com.arch.micro_service.auth_server.role.application.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.arch.micro_service.auth_server.role.application.service.role.RoleService;
import com.arch.micro_service.auth_server.role.domain.etntiy.Role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class RoleServiceTest {

  @Autowired
  private RoleService roleService;

  @Test
  void toggleActive() {
    Role role = roleService.toggleActive("1");
    assertFalse(role.isActive(), "Toggle is not active");
    role = roleService.toggleActive("1");
    assertTrue(role.isActive(), "Toggle is active");

  }

}
