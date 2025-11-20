package com.arch.micro_service.auth_server.user.domain.exception.type;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Email exception types
 */
@Getter
@AllArgsConstructor
public enum EmailExceptionType implements BaseExceptionType {

  NULL_EMAIL("Email cannot be null or empty."),
  MISSING_AT_SYMBOL("Email must contain '@' symbol."),
  MULTIPLE_AT_SYMBOLS("Email must contain only one '@' symbol."),
  EMPTY_LOCAL_PART("Email local part (before '@') cannot be empty."),
  EMPTY_DOMAIN_PART("Email domain part (after '@') cannot be empty."),
  INVALID_EMAIL_TOKEN("Invalid token");

  private final String message;

}
