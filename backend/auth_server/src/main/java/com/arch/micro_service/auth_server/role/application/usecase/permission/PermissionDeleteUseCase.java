package com.arch.micro_service.auth_server.role.application.usecase.permission;

import com.arch.micro_service.auth_server.role.application.constant.PermissionConstant;
import com.arch.micro_service.auth_server.role.application.service.PermissionService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class PermissionDeleteUseCase {

  private final PermissionService permissionService;

  public String delete(String id) {
    log.trace("Deleting permission");
    permissionService.deleteById(id);
    log.trace("Permission Deleted");
    return PermissionConstant.DELETE;
  }
}
