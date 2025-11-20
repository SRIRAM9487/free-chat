package com.arch.micro_service.auth_server.shared.infrastructure.exception;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiException;

import org.springframework.dao.DataIntegrityViolationException;
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
        .body(ApiException.notFound("No url match found", "URI_NOT_FOUND", request));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApiException> handleDateIntegretityException(DataIntegrityViolationException exception,
      HttpServletRequest request) {
    String message = extractMessage(exception);
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ApiException.notFound(message, "UNIQUE_VIOLATION", request));
  }

  private String extractMessage(DataIntegrityViolationException exception) {

    if (exception.getMessage().toLowerCase().contains("key (email)"))
      return "Email already exist";

    if (exception.getMessage().toLowerCase().contains("key (user_name)"))
      return "User name already exist";

    return exception.getMessage();

  }

}
