package com.arch.micro_service.auth_server.role.application.service.role.impl;

import java.util.ArrayList;
import java.util.List;

import com.arch.micro_service.auth_server.role.application.constant.RoleConstant;
import com.arch.micro_service.auth_server.role.application.service.role.RoleCrudService;
import com.arch.micro_service.auth_server.role.application.usecase.permission.PermissionFindUseCase;
import com.arch.micro_service.auth_server.role.application.usecase.role.RoleFindUseCase;
import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.domain.etntiy.RolePermission;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.mapper.RoleMapper;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RoleCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RolePermissionCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RoleDetailResponse;
import com.arch.micro_service.auth_server.role.infrastructure.persistence.RoleRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RoleCrudServiceImpl implements RoleCrudService {

  private final RoleRepository roleRepository;
  private final RoleMapper roleMapper;
  private final PermissionFindUseCase permissionFindUseCase;
  private final RoleFindUseCase roleFindUseCase;

  @Override
  public List<RoleDetailResponse> getAll() {
    var roles = roleRepository
        .findAll()
        .stream()
        .filter(p -> !p.isDeleted())
        .map(roleMapper::fromRole)
        .toList();
    return roles;
  }

  @Override
  public RoleDetailResponse get(String id) {
    return roleMapper.fromRole(roleFindUseCase.findById(id));
  }

  @Override
  public String create(RoleCreateRequest requestDto) {
    log.info("Creating role: {}", requestDto.title());
    Role role = roleMapper.toRole(requestDto);
    log.trace("Mapped Role entity: {}", role);
    List<RolePermission> rolePermissions = new ArrayList<>();
    for (var rolePermissionRequest : requestDto.rolePermissions()) {
      Permission permission = permissionFindUseCase.findById(rolePermissionRequest.permissionId());
      RolePermission rolePermission = RolePermission
          .builder()
          .active(rolePermissionRequest.active())
          .activeStatus(permission.isActive())
          .role(role)
          .permission(permission)
          .build();
      rolePermissions.add(rolePermission);
    }
    role.setRolePermissions(rolePermissions);
    log.trace("Final role entity before save: {}", role);
    roleRepository.save(role);
    log.info("Role created successfully: {}", requestDto.title());
    return RoleConstant.CREATE;
  }

  @Override
  public String update(String id, RoleCreateRequest requestDto) {

    Role role = roleFindUseCase.findById(id);
    roleMapper.updateRole(role, requestDto);

    List<RolePermission> rolePermissions = new ArrayList<>();
    for (RolePermissionCreateRequest createPermission : requestDto.rolePermissions()) {
      Permission permission = permissionFindUseCase.findById(createPermission.permissionId());
      RolePermission newPermission = roleMapper.toRolePermission(createPermission);
      newPermission.setPermission(permission);
      newPermission.setRole(role);

      rolePermissions.add(newPermission);
    }

    role.getRolePermissions().clear();
    role.getRolePermissions().addAll(rolePermissions);

    roleRepository.save(role);
    log.info("Updated role {} with {} permissions", id, rolePermissions.size());
    return RoleConstant.UPDATE;

  }

  @Override
  public String delete(String id) {
    log.trace("Attempting to delete role with id: {}", id);
    var role = roleFindUseCase.findById(id);
    role.softDelete();
    roleRepository.save(role);
    log.info("Successfully deleted role with id: {}", id);
    return RoleConstant.DELETE;
  }

}
