package com.arch.micro_service.auth_server.role.application.usecase.permission;

import com.arch.micro_service.auth_server.role.application.constant.PermissionConstant;
import com.arch.micro_service.auth_server.role.application.service.PermissionService;
import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.mapper.PermissionMapper;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request.PermissionCreateRequest;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j

public class PermissionUpdateUseCase {

  private final PermissionService permissionService;
  private final PermissionMapper permissionMapper;

  public String update(String id, PermissionCreateRequest createRequest) {
    log.trace("Permisssion update requested");
    Permission permission = permissionService.findById(id);
    permissionMapper.update(permission, createRequest);
    permissionService.save(permission);
    log.trace("Permisssion updated");
    return PermissionConstant.UPDATE;
  }

}
