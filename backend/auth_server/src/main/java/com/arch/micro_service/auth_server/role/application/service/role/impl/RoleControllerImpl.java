package com.arch.micro_service.auth_server.role.application.service.role.impl;

import java.util.List;

import com.arch.micro_service.auth_server.role.application.service.role.RoleService;
import com.arch.micro_service.auth_server.role.application.usecase.role.RoleFindUseCase;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.mapper.RoleMapper;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RoleUserMetaDataResponse;
import com.arch.micro_service.auth_server.role.infrastructure.persistence.RoleRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleService {

  private final RoleRepository roleRepository;
  private final RoleFindUseCase roleFindUseCase;
  private final RoleMapper roleMapper;

  @Override
  public String toggleActive(String id) {

    var role = roleFindUseCase.findById(id);

    role.toggleActive();

    roleRepository.save(role);

    return role.getTitle() + "Locked ";

  }

  @Override
  public List<RoleUserMetaDataResponse> fetchRole() {
    var roles = roleFindUseCase.findAll();

    return roles.stream().map(roleMapper::toRoleUserMetaData).toList();
  }

}
