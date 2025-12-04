package com.arch.micro_service.auth_server.role.application.service.role.impl;

import java.util.ArrayList;
import java.util.List;

import com.arch.micro_service.auth_server.role.application.service.role.RoleCrudService;
import com.arch.micro_service.auth_server.role.application.usecase.permission.PermissionFindUseCase;
import com.arch.micro_service.auth_server.role.application.usecase.role.RoleFindUseCase;
import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.domain.etntiy.RolePermission;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.mapper.RoleMapper;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RoleCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RolePermissionCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.persistence.RoleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleCrudServiceImpl implements RoleCrudService {

  private final RoleRepository roleRepository;
  private final RoleMapper roleMapper;
  private final PermissionFindUseCase permissionFindUseCase;
  private final RoleFindUseCase roleFindUseCase;
  private final Logger log = LoggerFactory.getLogger("MethodLogger");

  @Override
  public List<Role> getAll() {
    return roleRepository
        .findAll()
        .stream()
        .filter(p -> !p.isDeleted())
        .toList();
  }

  @Override
  public Role get(String id) {
    return roleFindUseCase.findById(id);
  }

  @Override
  public Role create(RoleCreateRequest requestDto) {
    log.info("Creating role: {}", requestDto.title());
    Role role = roleMapper.toRole(requestDto);
    log.trace("Mapped Role entity: {}", role);
    List<RolePermission> rolePermissions = new ArrayList<>();

    for (var rolePermissionRequest : requestDto.rolePermissions()) {
      Permission permission = permissionFindUseCase.findById(rolePermissionRequest.permissionId());
      RolePermission rolePermission = RolePermission
          .builder()
          .active(rolePermissionRequest.active())
          .role(role)
          .permission(permission)
          .build();
      rolePermissions.add(rolePermission);
    }

    role.setRolePermissions(rolePermissions);
    log.trace("Final role entity before save: {}", role);
    roleRepository.save(role);
    log.info("Role created successfully: {}", requestDto.title());
    return role;
  }

  @Override
  public Role update(String id, RoleCreateRequest requestDto) {

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
    return role;

  }

  @Override
  public Role delete(String id) {
    log.trace("Attempting to delete role with id: {}", id);
    var role = roleFindUseCase.findById(id);
    role.softDelete();
    for (var user : role.getUsers()) {
      user.getRoles().remove(role);
    }
    roleRepository.save(role);
    log.info("Successfully deleted role with id: {}", id);
    return role;
  }

}
