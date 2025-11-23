package com.arch.micro_service.auth_server.user.domain.exception.type;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PasswordExceptionType implements BaseExceptionType {

  ATLEAST_ONE_LOWERCASE_LETTER("Password must contain at least one lowercase letter", HttpStatus.BAD_REQUEST),
  ATLEAST_ONE_CAPITAL_LETTER("Password must contain at least one uppercase letter", HttpStatus.BAD_REQUEST),
  ATLEAST_ONE_NUMBER("Password must contain at least one number", HttpStatus.BAD_REQUEST),
  ATLEAST_EIGHT_CHARACTERS("Password must be at least 8 characters long", HttpStatus.BAD_REQUEST),
  NOT_VALID_TOKEN("Invalid token", HttpStatus.UNAUTHORIZED),
  NULL_PASSWORD("Password cannot be empty", HttpStatus.BAD_REQUEST);

  private final String message;
  private final HttpStatus httpStatus;
}
