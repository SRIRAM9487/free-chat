package com.arch.micro_service.auth_server.user.infrastructure.exception;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiException;
import com.arch.micro_service.auth_server.user.domain.exception.UserException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class UserControllerException {

  @ExceptionHandler(UserException.class)
  public ResponseEntity<ApiException> handleUserException(UserException exception,
      HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiException.create(exception, request));
  }

}
