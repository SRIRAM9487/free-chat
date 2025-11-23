package com.arch.micro_service.auth_server.role.application.service.permission.impl;

import java.util.List;

import com.arch.micro_service.auth_server.role.application.constant.PermissionConstant;
import com.arch.micro_service.auth_server.role.application.service.permission.PermissionCrudService;
import com.arch.micro_service.auth_server.role.application.usecase.permission.PermissionFindUseCase;
import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.mapper.PermissionMapper;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request.PermissionCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.response.PermissionDetailResponse;
import com.arch.micro_service.auth_server.role.infrastructure.persistence.PermissionRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PermissionCrudServiceImpl implements PermissionCrudService {

  private final PermissionRepository permissionRepository;
  private final PermissionMapper permissionMapper;
  private final PermissionFindUseCase permissionFindUseCase;

  @Override
  public List<PermissionDetailResponse> getAll() {
    var permissions = permissionRepository
        .findAll()
        .stream()
        .filter(p -> !p.isDeleted())
        .map(permissionMapper::fromPermission)
        .toList();
    return permissions;
  }

  @Override
  public PermissionDetailResponse get(String id) {
    return permissionMapper.fromPermission(permissionFindUseCase.findById(id));
  }

  @Override
  public String create(PermissionCreateRequest requestDto) {
    Permission permission = permissionMapper.toPermission(requestDto);
    permissionRepository.save(permission);
    log.trace("Permsision created");
    return PermissionConstant.CREATE;
  }

  @Override
  public String update(String id, PermissionCreateRequest requestDto) {
    Permission permission = permissionFindUseCase.findById(id);
    permissionMapper.update(permission, requestDto);
    permissionRepository.save(permission);
    log.trace("Permisssion updated");
    return PermissionConstant.UPDATE;
  }

  @Override
  public String delete(String id) {
    Permission permission = permissionFindUseCase.findById(id);
    permission.softDelete();
    permissionRepository.save(permission);
    log.trace("Permission Deleted");
    return PermissionConstant.DELETE;
  }

}
