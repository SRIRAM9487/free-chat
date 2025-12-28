package com.arch.micro_service.auth_server.user.domain.exception.type;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Email exception types
 */
@Getter
@AllArgsConstructor
public enum EmailExceptionType implements BaseExceptionType {

  UNIQUE_EMAIL("Email already exists", HttpStatus.BAD_REQUEST),
  NULL_EMAIL("Email cannot be null or empty", HttpStatus.BAD_REQUEST),
  MISSING_AT_SYMBOL("Email must contain '@' symbol", HttpStatus.BAD_REQUEST),
  MULTIPLE_AT_SYMBOLS("Email must contain only one '@' symbol", HttpStatus.BAD_REQUEST),
  EMPTY_LOCAL_PART("Email local part (before '@') cannot be empty", HttpStatus.BAD_REQUEST),
  EMPTY_DOMAIN_PART("Email domain part (after '@') cannot be empty", HttpStatus.BAD_REQUEST),
  INVALID_EMAIL_TOKEN("Invalid token", HttpStatus.UNAUTHORIZED);

  private final String message;
  private final HttpStatus httpStatus;
}
