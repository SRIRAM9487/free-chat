package com.arch.micro_service.auth_server.role.application.service.role;

import com.arch.micro_service.auth_server.role.domain.etntiy.Role;

public interface RoleService {

  Role toggleActive(String id);

}
