package com.arch.micro_service.auth_server.role.application.service;

import java.util.List;

import com.arch.micro_service.auth_server.role.application.service.permission.PermissionCrudService;
import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PermissionCrudServiceTest {

  @Autowired
  private PermissionCrudService permissionCrudService;

  @Test
  void getAll() {
    List<Permission> hell = permissionCrudService.getAll();

  }

}
