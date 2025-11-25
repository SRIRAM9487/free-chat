package com.arch.micro_service.auth_server.role.domain.exception.type;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RoleExceptionType implements BaseExceptionType {

  ROLE_TITLE_UNIQUE("Role must be unique", HttpStatus.CONFLICT),
  ROLE_NOT_FOUND("Role not found", HttpStatus.NOT_FOUND);

  private final String message;
  private final HttpStatus httpStatus;
}
