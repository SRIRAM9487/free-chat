package com.arch.micro_service.auth_server.shared.infrastructure.exception;

import com.arch.micro_service.auth_server.role.domain.exception.PermissionException;
import com.arch.micro_service.auth_server.role.domain.exception.RoleException;
import com.arch.micro_service.auth_server.shared.domain.exception.BaseException;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiException;
import com.arch.micro_service.auth_server.user.domain.exception.UserException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class DataIntegrityViolationExceptionAdvice {

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApiException> handleDateIntegretityException(DataIntegrityViolationException exception,
      HttpServletRequest request) {
    BaseException baseException = extractException(exception);
    return ResponseEntity
        .status(baseException.getHttpStatus())
        .body(ApiException.conflict(baseException, request));
  }

  private BaseException extractException(DataIntegrityViolationException exception) {

    if (exception.getLocalizedMessage().contains("permissions_title_key"))
      return PermissionException.titleUniqueKeyViolation();

    if (exception.getLocalizedMessage().contains("roles_title_key"))
      return RoleException.titleUniqueKeyViolation();

    if (exception.getLocalizedMessage().contains("users_email_key"))
      return UserException.emailNotUniqueViolation();

    if (exception.getLocalizedMessage().contains("users_user_name_key"))
      return UserException.userNameNotUniqueViolation();

    return null;
  }

}
