package com.arch.micro_service.auth_server.shared.infrastructure.exception;

import com.arch.micro_service.auth_server.security.exception.CustomNoHandlerFoundException;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class HttpControllerAdvice {

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ApiException> handleNoHandlerFoundException(NoHandlerFoundException exception,
      HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ApiException.notFound(CustomNoHandlerFoundException.uriNotFound(), request));
  }

}
