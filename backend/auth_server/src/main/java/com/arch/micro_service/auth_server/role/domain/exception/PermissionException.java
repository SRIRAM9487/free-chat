package com.arch.micro_service.auth_server.role.domain.exception;

import com.arch.micro_service.auth_server.role.domain.exception.type.PermissionExceptionType;
import com.arch.micro_service.auth_server.shared.domain.exception.BaseException;
import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PermissionException extends BaseException {

  public PermissionException(BaseExceptionType type) {
    super(type);
  }

  public static PermissionException notFound() {
    log.trace("Permission  not found");
    return new PermissionException(PermissionExceptionType.PERMISSION_NOT_FOUND);
  }

}
