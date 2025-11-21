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

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class RoleCreateUseCase {

  private final RoleService roleService;
  private final RoleMapper roleMapper;
  private final PermissionService permissionService;

  public String create(RoleCreateRequest createRequest) {

    log.info("Creating role: {}", createRequest.title());

    Role role = roleMapper.toRole(createRequest);
    log.trace("Mapped Role entity: {}", role);

    List<RolePermission> rolePermissions = new ArrayList<>();

    for (var rolePermissionRequest : createRequest.rolePermissions()) {

      Permission permission = permissionService.findById(rolePermissionRequest.permissionId());

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
    roleService.save(role);

    log.info("Role created successfully: {}", createRequest.title());

    return RoleConstant.CREATE;
  }

}
