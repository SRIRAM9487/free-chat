package com.arch.micro_service.auth_server.security.exception;

import com.arch.micro_service.auth_server.security.exception.type.CustomNoHandlerFoundExceptionType;
import com.arch.micro_service.auth_server.shared.domain.exception.BaseException;
import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;

public class CustomNoHandlerFoundException extends BaseException {

  public CustomNoHandlerFoundException(BaseExceptionType type) {
    super(type);
  }

  public static CustomNoHandlerFoundException uriNotFound() {
    return new CustomNoHandlerFoundException(CustomNoHandlerFoundExceptionType.NO_URI_FOUND);
  }

}
