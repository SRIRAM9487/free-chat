package com.arch.micro_service.auth_server.role.application.usecase.permission;

import java.util.List;

import com.arch.micro_service.auth_server.role.application.service.PermissionService;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.mapper.PermissionMapper;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.response.PermissionDetailResponse;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j

public class PermissionReadUseCase {

  private final PermissionService permissionService;
  private final PermissionMapper permissionMapper;

  public List<PermissionDetailResponse> getAll() {
    return permissionService.findAll().stream().map(permissionMapper::fromPermission).toList();
  }

  public PermissionDetailResponse getById(String id) {
    return permissionMapper.fromPermission(permissionService.findById(id));
  }

}
