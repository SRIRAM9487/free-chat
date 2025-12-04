package com.arch.micro_service.auth_server.role.domain.exception;

import com.arch.micro_service.auth_server.role.domain.exception.type.RoleExceptionType;
import com.arch.micro_service.auth_server.shared.domain.exception.BaseException;
import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;

public class RoleException extends BaseException {

  public RoleException(BaseExceptionType type) {
    super(type);
  }

  public static RoleException notFound() {
    return new RoleException(RoleExceptionType.ROLE_NOT_FOUND);
  }

  public static BaseException titleUniqueKeyViolation() {
    return new RoleException(RoleExceptionType.ROLE_TITLE_UNIQUE);
  }

}
