package com.arch.micro_service.auth_server.role.application.service;

import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.domain.exception.PermissionException;
import com.arch.micro_service.auth_server.role.infrastructure.persistence.PermissionRepository;
import com.arch.micro_service.auth_server.shared.application.service.AbstractCrudService;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PermissionService extends AbstractCrudService<Permission, PermissionException> {

  private final PermissionRepository permissionRepository;

  public PermissionService(PermissionRepository repository) {
    super(repository);
    this.permissionRepository = repository;
  }

  public Permission findByTitle(String title) {
    Permission permission = permissionRepository.findByTitle(title).orElseThrow(PermissionException::notFound);
    if (permission.isDeleted()) {
      throw PermissionException.notFound();
    }
    return permission;
  }

  @Override
  public PermissionException notFound() {
    return PermissionException.notFound();
  }

}
