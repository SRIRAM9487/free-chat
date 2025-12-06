package com.arch.micro_service.auth_server.role.application.usecase.permission;

import com.arch.micro_service.auth_server.log.CustomLogger;
import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.domain.exception.PermissionException;
import com.arch.micro_service.auth_server.role.infrastructure.persistence.PermissionRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionFindUseCase {

  private final PermissionRepository permissionRepository;
  private final CustomLogger customLogger;

  public Permission findById(String id) {

    Permission permission = permissionRepository.findById(Long.parseLong(id))
        .orElseThrow(() -> {
          PermissionException exception = PermissionException.notFound();
          customLogger.failure("Permission not found " + id, exception);
          return exception;
        });

    if (permission.isDeleted()) {
      PermissionException exception = PermissionException.notFound();
      customLogger.failure("Permission not found " + id, exception);
      throw exception;
    }
    return permission;
  }
}
