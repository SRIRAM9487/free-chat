package com.arch.micro_service.auth_server.role.application.service;

import com.arch.micro_service.auth_server.role.domain.etntiy.RolePermission;
import com.arch.micro_service.auth_server.role.domain.exception.RolePermissionException;
import com.arch.micro_service.auth_server.role.infrastructure.persistence.RolePermissionRepository;
import com.arch.micro_service.auth_server.shared.application.service.AbstractCrudService;

import org.springframework.stereotype.Service;

@Service
public class RolePermissionService extends AbstractCrudService<RolePermission, RolePermissionException> {

  public RolePermissionService(RolePermissionRepository repository) {
    super(repository);
  }

  @Override
  public RolePermissionException notFound() {
    return RolePermissionException.notFound();
  }

}
