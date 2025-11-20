package com.arch.micro_service.auth_server.security.exception;

import com.arch.micro_service.auth_server.security.exception.type.UnAuthorizedExceptionType;
import com.arch.micro_service.auth_server.shared.domain.exception.BaseException;
import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;

public class UnAuthorizedException extends BaseException {

  public UnAuthorizedException(BaseExceptionType type) {
    super(type);
  }

  public static UnAuthorizedException accessDenied() {
    return new UnAuthorizedException(UnAuthorizedExceptionType.ACCESS_DENIED);
  }

}
