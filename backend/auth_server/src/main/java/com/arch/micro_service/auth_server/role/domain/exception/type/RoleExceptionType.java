package com.arch.micro_service.auth_server.role.domain.exception.type;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleExceptionType implements BaseExceptionType {

  ROLE_NOT_FOUND("Role not found");

  private final String message;

}
