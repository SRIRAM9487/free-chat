package com.arch.micro_service.auth_server.role.domain.exception;

import com.arch.micro_service.auth_server.role.domain.exception.type.PermissionExceptionType;
import com.arch.micro_service.auth_server.shared.domain.exception.BaseException;
import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;

public class PermissionException extends BaseException {

  public PermissionException(BaseExceptionType type) {
    super(type);
  }

  public static PermissionException notFound() {
    return new PermissionException(PermissionExceptionType.PERMISSION_NOT_FOUND);
  }

  public static PermissionException titleUniqueKeyViolation() {
    return new PermissionException(PermissionExceptionType.UNIQUE_TITLE);
  }

}
