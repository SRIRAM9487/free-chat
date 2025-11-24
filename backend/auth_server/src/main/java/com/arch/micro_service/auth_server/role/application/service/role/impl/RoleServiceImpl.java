package com.arch.micro_service.auth_server.role.application.service.role.impl;

import com.arch.micro_service.auth_server.role.application.service.role.RoleService;
import com.arch.micro_service.auth_server.role.application.usecase.role.RoleFindUseCase;
import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.infrastructure.persistence.RoleRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;
  private final RoleFindUseCase roleFindUseCase;

  @Override
  public Role toggleActive(String id) {

    var role = roleFindUseCase.findById(id);

    role.toggleActive();

    roleRepository.save(role);

    return role;

  }

}
