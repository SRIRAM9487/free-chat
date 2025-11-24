package com.arch.micro_service.auth_server.role.application.service.permission;

import java.util.List;

import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request.PermissionCreateRequest;

/**
 * Permission CRUD
 */
public interface PermissionCrudService {

  List<Permission> getAll();

  Permission get(String id);

  Permission create(PermissionCreateRequest requestDto);

  Permission update(String id, PermissionCreateRequest requestDto);

  Permission delete(String id);

}
