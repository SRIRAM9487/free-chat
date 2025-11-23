package com.arch.micro_service.auth_server.role.application.usecase.permission;

import java.util.UUID;

import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.domain.exception.PermissionException;
import com.arch.micro_service.auth_server.role.infrastructure.persistence.PermissionRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionFindUseCase {

  private final PermissionRepository permissionRepository;

  public Permission findById(String id) {
    Permission permission = permissionRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> PermissionException.notFound(id));
    if (permission.isDeleted()) {
      throw PermissionException.notFound(id);
    }
    return permission;
  }
}
