package com.arch.micro_service.auth_server.role.application.usecase.permission;

import com.arch.micro_service.auth_server.role.application.constant.PermissionConstant;
import com.arch.micro_service.auth_server.role.application.service.PermissionService;
import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.mapper.PermissionMapper;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request.PermissionCreateRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class PermissionCreateUseCase {

  private final PermissionMapper permissionMapper;
  private final PermissionService permissionService;

  @Transactional
  public String create(PermissionCreateRequest createRequest) {
    Permission permission = permissionMapper.toPermission(createRequest);
    log.trace("Permsision Mapped");
    permissionService.save(permission);
    log.trace("Permsision created");
    return PermissionConstant.CREATE;

  }
}
