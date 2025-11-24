package com.arch.micro_service.auth_server.role.application.service.role;

import java.util.List;

import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RoleUserMetaDataResponse;

public interface RoleService {

  String toggleActive(String id);

  List<RoleUserMetaDataResponse> fetchRole();

}
