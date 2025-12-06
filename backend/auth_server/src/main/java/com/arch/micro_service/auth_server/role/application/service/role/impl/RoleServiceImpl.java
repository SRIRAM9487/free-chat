package com.arch.micro_service.auth_server.role.application.service.role.impl;

import com.arch.micro_service.auth_server.log.CustomLogger;
import com.arch.micro_service.auth_server.role.application.service.role.RoleService;
import com.arch.micro_service.auth_server.role.application.usecase.role.RoleFindUseCase;
import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.infrastructure.persistence.RoleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;
  private final RoleFindUseCase roleFindUseCase;
  private final CustomLogger customLogger;
  private final Logger log = LoggerFactory.getLogger("MethodLogger");

  @Override
  public Role toggleActive(String id) {
    var role = roleFindUseCase.findById(id);
    role.toggleActive();
    var toggledRole = roleRepository.save(role);
    log.trace("role toggled : {}", toggledRole);
    customLogger.success("Role active toggled", toggledRole, role);
    return role;
  }

}
