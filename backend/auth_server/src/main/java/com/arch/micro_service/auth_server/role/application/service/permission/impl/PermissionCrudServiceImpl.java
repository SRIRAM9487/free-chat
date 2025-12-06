package com.arch.micro_service.auth_server.role.application.service.permission.impl;

import java.util.List;

import com.arch.micro_service.auth_server.log.CustomLogger;
import com.arch.micro_service.auth_server.role.application.service.permission.PermissionCrudService;
import com.arch.micro_service.auth_server.role.application.usecase.permission.PermissionFindUseCase;
import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.mapper.PermissionMapper;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request.PermissionCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.persistence.PermissionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PermissionCrudServiceImpl implements PermissionCrudService {

  private final PermissionRepository permissionRepository;
  private final PermissionMapper permissionMapper;
  private final PermissionFindUseCase permissionFindUseCase;
  private final CustomLogger customLogger;
  private final Logger log = LoggerFactory.getLogger("MethodLogger");

  @Override
  public List<Permission> getAll() {
    var permissions = permissionRepository
        .findAll()
        .stream()
        .filter(p -> !p.isDeleted())
        .toList();
    return permissions;
  }

  @Override
  public Permission get(String id) {
    return permissionFindUseCase.findById(id);
  }

  @Override
  public Permission create(PermissionCreateRequest requestDto) {
    Permission permission = permissionMapper.toPermission(requestDto);
    Permission savedPermission = permissionRepository.save(permission);
    log.trace("Permission saved : {}", savedPermission);
    customLogger.success("Permission Created", savedPermission, "new");
    return savedPermission;
  }

  @Override
  public Permission update(String id, PermissionCreateRequest requestDto) {
    Permission permission = permissionFindUseCase.findById(id);
    permissionMapper.update(permission, requestDto);
    Permission updatePermission = permissionRepository.save(permission);
    log.trace("Permission updated");
    customLogger.success("Permission Updated", updatePermission, permission);
    return updatePermission;
  }

  @Override
  public Permission delete(String id) {
    Permission permission = permissionFindUseCase.findById(id);
    permission.softDelete();
    Permission deletedPermission = permissionRepository.save(permission);
    deletedPermission.getRolePermission().clear();
    log.trace("Permission RolePermission removed {}", deletedPermission);
    customLogger.success("Permission Deleted", deletedPermission, permission);
    return deletedPermission;
  }

}
