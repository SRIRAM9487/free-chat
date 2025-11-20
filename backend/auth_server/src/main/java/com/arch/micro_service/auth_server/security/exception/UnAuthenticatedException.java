package com.arch.micro_service.auth_server.security.exception;

import com.arch.micro_service.auth_server.security.exception.type.UnAuthenticatedExceptionType;
import com.arch.micro_service.auth_server.shared.domain.exception.BaseException;
import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;

public class UnAuthenticatedException extends BaseException {

  public UnAuthenticatedException(BaseExceptionType type) {
    super(type);
  }

  public static UnAuthenticatedException authenticationFailed() {
    return new UnAuthenticatedException(UnAuthenticatedExceptionType.AUTHENTICATION_FAILED);
  }

}
