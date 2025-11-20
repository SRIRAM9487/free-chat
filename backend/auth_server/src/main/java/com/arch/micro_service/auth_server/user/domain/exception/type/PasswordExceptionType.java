package com.arch.micro_service.auth_server.user.domain.exception.type;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PasswordExceptionType implements BaseExceptionType {

  ATLEAST_ONE_LOWERCASE_LETTER("Password must contain at least one lowercase letter."),
  ATLEAST_ONE_CAPITAL_LETTER("Password must contain at least one uppercase letter."),
  ATLEAST_ONE_NUMBER("Password must contain at least one number."),
  ATLEAST_EIGHT_CHARACTERS("Password must be at least 8 characters long."),
  NOT_VALID_TOKEN("Invalid token"),
  NULL_PASSWORD("Password cannot be empty");

  public String message;

}
