package com.arch.micro_service.auth_server.security.exception.type;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnAuthenticatedExceptionType implements BaseExceptionType {

  AUTHENTICATION_FAILED("Invalid UserName or Password");

  private final String message;

}
