package com.arch.micro_service.auth_server.role.application.usecase.role;

import java.util.List;

import com.arch.micro_service.auth_server.log.CustomLogger;
import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.domain.exception.RoleException;
import com.arch.micro_service.auth_server.role.infrastructure.persistence.RoleRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleFindUseCase {

  private final RoleRepository roleRepository;
  private final CustomLogger customLogger;

  public List<Role> findAll() {
    var roles = roleRepository.findAll();
    return roles.stream().filter(role -> !role.isDeleted()).toList();
  }

  public List<Role> findAllById(List<Long> ids) {

    var roles = roleRepository.findAllById(ids);

    return roles.stream().filter(role -> !role.isDeleted()).toList();

  }

  public Role findById(String id) {

    var role = roleRepository.findById(Long.parseLong(id)).orElseThrow(
        () -> {
          RoleException ex = RoleException.notFound();
          customLogger.failure("Role not found " + id, ex);
          return ex;
        });

    if (role.isDeleted()) {
      RoleException ex = RoleException.notFound();
      customLogger.failure("Role not found " + id, ex);
      throw ex;
    }

    return role;
  }

}
