package com.arch.micro_service.auth_server.role.application.service;

import com.arch.micro_service.auth_server.role.application.service.permission.PermissionCrudService;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class PermissionCrudServiceTest {

  @Autowired
  private PermissionCrudService permissionCrudService;

}
