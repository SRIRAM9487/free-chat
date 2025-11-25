package com.arch.micro_service.auth_server.role.domain.exception.type;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PermissionExceptionType implements BaseExceptionType {

  UNIQUE_TITLE("Permission already exists", HttpStatus.CONFLICT),
  PERMISSION_NOT_FOUND("Permission not found", HttpStatus.NOT_FOUND);

  private final String message;
  private final HttpStatus httpStatus;
}
