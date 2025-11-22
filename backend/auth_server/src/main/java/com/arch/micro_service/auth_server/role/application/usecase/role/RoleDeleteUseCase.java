package com.arch.micro_service.auth_server.role.application.usecase.role;

import com.arch.micro_service.auth_server.role.application.constant.RoleConstant;
import com.arch.micro_service.auth_server.role.application.service.RoleService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class RoleDeleteUseCase {

  private final RoleService roleService;

  public String delete(String id) {
    log.trace("Attempting to delete role with id: {}", id);
    roleService.deleteById(id);
    log.info("Successfully deleted role with id: {}", id);
    return RoleConstant.DELETE;
  }
}
