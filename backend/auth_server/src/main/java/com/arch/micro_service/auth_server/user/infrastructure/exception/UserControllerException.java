package com.arch.micro_service.auth_server.user.infrastructure.exception;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiException;
import com.arch.micro_service.auth_server.user.domain.exception.EmailException;
import com.arch.micro_service.auth_server.user.domain.exception.PasswordException;
import com.arch.micro_service.auth_server.user.domain.exception.PhoneNumberException;
import com.arch.micro_service.auth_server.user.domain.exception.UserException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class UserControllerException {

  @ExceptionHandler(EmailException.class)
  public ResponseEntity<ApiException> handleEmailException(EmailException exception,
      HttpServletRequest request) {
    return ResponseEntity.status(exception.getHttpStatus()).body(ApiException.create(exception, request));
  }

  @ExceptionHandler(PasswordException.class)
  public ResponseEntity<ApiException> handlePasswordException(PasswordException exception,
      HttpServletRequest request) {
    return ResponseEntity.status(exception.getHttpStatus()).body(ApiException.create(exception, request));
  }

  @ExceptionHandler(PhoneNumberException.class)
  public ResponseEntity<ApiException> handlePhoneNumberException(PhoneNumberException exception,
      HttpServletRequest request) {
    return ResponseEntity.status(exception.getHttpStatus()).body(ApiException.create(exception, request));
  }

  @ExceptionHandler(UserException.class)
  public ResponseEntity<ApiException> handleUserException(UserException exception,
      HttpServletRequest request) {
    return ResponseEntity.status(exception.getHttpStatus()).body(ApiException.create(exception, request));
  }

}
