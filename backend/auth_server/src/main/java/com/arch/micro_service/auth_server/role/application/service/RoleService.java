package com.arch.micro_service.auth_server.role.application.service;

import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.domain.exception.RoleException;
import com.arch.micro_service.auth_server.role.infrastructure.persistence.RoleRepository;
import com.arch.micro_service.auth_server.shared.application.service.AbstractCrudService;

import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractCrudService<Role, RoleException> {

  public RoleService(RoleRepository repository) {
    super(repository);
  }

  @Override
  public RoleException notFound() {
    return RoleException.notFound();
  }

}
