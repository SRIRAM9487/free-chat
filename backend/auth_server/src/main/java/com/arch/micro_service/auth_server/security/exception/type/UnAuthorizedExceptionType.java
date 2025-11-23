package com.arch.micro_service.auth_server.security.exception.type;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UnAuthorizedExceptionType implements BaseExceptionType {

  ACCESS_DENIED("Access Denied", HttpStatus.FORBIDDEN);

  private final String message;
  private final HttpStatus httpStatus;
}
