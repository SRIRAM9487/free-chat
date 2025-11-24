package com.arch.micro_service.auth_server.role.application.service.role;

import java.util.List;

import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RoleCreateRequest;

public interface RoleCrudService {

  List<Role> getAll();

  Role get(String id);

  Role create(RoleCreateRequest requestDto);

  Role update(String id, RoleCreateRequest requestDto);

  Role delete(String id);

}
