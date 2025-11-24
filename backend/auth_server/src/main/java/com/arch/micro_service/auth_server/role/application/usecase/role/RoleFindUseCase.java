package com.arch.micro_service.auth_server.role.application.usecase.role;

import java.util.List;

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

    var role = roleRepository.findById(Long.parseLong(id)).orElseThrow(() -> RoleException.notFound(id));
    if (role.isDeleted()) {
      throw RoleException.notFound(id);
    }
    return role;
  }

  public List<Role> findAll() {

    var roles = roleRepository.findAll();

    return roles.stream().filter(role -> !role.isDeleted()).toList();

  }

  public List<Role> findAllById(List<Long> ids) {

    var roles = roleRepository.findAllById(ids);

    return roles.stream().filter(role -> !role.isDeleted()).toList();

  }

  public List<Role> findAllByListId(List<String> id) {

    List<Long> ids = id.stream().map(Long::parseLong).toList();

    var roles = roleRepository.findAllById(ids);

    return roles.stream().filter(role -> !role.isDeleted()).toList();

  }

}
