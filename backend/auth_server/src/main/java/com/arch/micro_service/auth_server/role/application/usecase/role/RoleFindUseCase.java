package com.arch.micro_service.auth_server.role.application.usecase.role;

import java.util.UUID;

import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.domain.exception.RoleException;
import com.arch.micro_service.auth_server.role.infrastructure.persistence.RoleRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleFindUseCase {

  private final RoleRepository roleRepository;

  public Role findById(String id) {

    var role = roleRepository.findById(UUID.fromString(id)).orElseThrow(() -> RoleException.notFound(id));
    if (role.isDeleted()) {
      throw RoleException.notFound(id);
    }
    return role;
  }

}
