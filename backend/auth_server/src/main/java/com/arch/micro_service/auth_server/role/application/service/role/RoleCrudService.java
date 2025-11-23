package com.arch.micro_service.auth_server.role.application.service.role;

import java.util.List;

import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RoleCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RoleDetailResponse;

public interface RoleCrudService {

  List<RoleDetailResponse> getAll();

  RoleDetailResponse get(String id);

  String create(RoleCreateRequest requestDto);

  String update(String id, RoleCreateRequest requestDto);

  String delete(String id);

}
