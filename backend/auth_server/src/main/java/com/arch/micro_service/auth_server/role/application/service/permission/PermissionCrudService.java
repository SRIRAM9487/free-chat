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

  String create(PermissionCreateRequest requestDto);

  String update(String id, PermissionCreateRequest requestDto);

  String delete(String id);

}
