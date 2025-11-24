package com.arch.micro_service.auth_server.role.application.service.role;

import java.util.List;

import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RoleCreateRequest;

public interface RoleCrudService {

  List<Role> getAll();

  Role get(String id);

  String create(RoleCreateRequest requestDto);

  String update(String id, RoleCreateRequest requestDto);

  String delete(String id);

}
