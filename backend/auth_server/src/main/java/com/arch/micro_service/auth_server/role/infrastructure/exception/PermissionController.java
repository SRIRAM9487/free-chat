package com.arch.micro_service.auth_server.role.infrastructure.exception;

import com.arch.micro_service.auth_server.role.domain.exception.PermissionException;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class PermissionController {

  @ExceptionHandler(PermissionException.class)
  public ResponseEntity<ApiException> handlePermissionException(PermissionException exception,
      HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiException.create(exception, request));
  }
}
