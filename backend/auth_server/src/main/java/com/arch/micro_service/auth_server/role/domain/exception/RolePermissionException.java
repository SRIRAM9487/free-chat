package com.arch.micro_service.auth_server.role.domain.exception;

import com.arch.micro_service.auth_server.role.domain.exception.type.RolePermissionExceptionType;
import com.arch.micro_service.auth_server.shared.domain.exception.BaseException;
import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;

public class RolePermissionException extends BaseException {

  public RolePermissionException(BaseExceptionType type) {
    super(type);
  }

  public static RolePermissionException notFound() {
    return new RolePermissionException(RolePermissionExceptionType.ROLE_PERMISSION_NOT_FOUND);
  }

}
