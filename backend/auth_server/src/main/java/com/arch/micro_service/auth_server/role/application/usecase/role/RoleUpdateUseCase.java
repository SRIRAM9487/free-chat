package com.arch.micro_service.auth_server.role.application.usecase.role;

import java.util.ArrayList;
import java.util.List;

import com.arch.micro_service.auth_server.role.application.constant.RoleConstant;
import com.arch.micro_service.auth_server.role.application.service.PermissionService;
import com.arch.micro_service.auth_server.role.application.service.RoleService;
import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.domain.etntiy.RolePermission;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.mapper.RoleMapper;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RoleCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RolePermissionCreateRequest;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j

public class RoleUpdateUseCase {

  private final RoleService roleService;
  private final RoleMapper roleMapper;
  private final PermissionService permissionService;

  public String update(String id, RoleCreateRequest createRequest) {

    Role role = roleService.findById(id);

    roleMapper.updateRole(role, createRequest);

    List<RolePermission> rolePermissions = new ArrayList<>();

    for (RolePermissionCreateRequest createPermission : createRequest.rolePermissions()) {
      Permission permission = permissionService.findById(createPermission.permissionId());
      RolePermission newPermisison = roleMapper.toRolePermission(createPermission);
      newPermisison.setPermission(permission);
      rolePermissions.add(newPermisison);
    }
    role.setRolePermissions(rolePermissions);

    roleService.save(role);

    return RoleConstant.UPDATE;
  }

}
