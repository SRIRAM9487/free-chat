package com.arch.micro_service.auth_server.role.application.usecase.role;

import java.util.List;

import com.arch.micro_service.auth_server.role.application.service.RoleService;
import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.mapper.RoleMapper;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RoleDetailResponse;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class RoleReadUseCase {

  private final RoleService roleService;
  private final RoleMapper roleMapper;

  public List<RoleDetailResponse> getAll() {
    List<Role> roles = roleService.findAll();
    return roles.stream().map(roleMapper::fromRole).toList();
  }

  public RoleDetailResponse getById(String id) {
    Role role = roleService.findById(id);
    return roleMapper.fromRole(role);
  }
}
