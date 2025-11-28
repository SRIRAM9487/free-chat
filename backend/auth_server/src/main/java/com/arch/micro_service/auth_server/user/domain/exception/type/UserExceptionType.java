package com.arch.micro_service.auth_server.user.domain.exception.type;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserExceptionType implements BaseExceptionType {

  USER_EMAIL_MUST_BE_UNIQUE("User Email already exists", HttpStatus.CONFLICT),
  USER_NAME_MUST_BE_UNIQUE("User name already exists", HttpStatus.CONFLICT),
  USER_NOT_FOUND("User not found", HttpStatus.NOT_FOUND),
  AUTHENTICATION_FAILED("Authentication failed", HttpStatus.UNAUTHORIZED),
  EMAIL_NOT_VERIFIED("Email not verified", HttpStatus.FORBIDDEN),
  INVALID_OTP("Invalid OTP", HttpStatus.UNAUTHORIZED),
  INVALID_PASSWORD_VERIFICATION_TOKEN("Invalid token", HttpStatus.UNAUTHORIZED),
  USER_NAME_NOT_FOUND("User name not found", HttpStatus.NOT_FOUND);

  private final String message;
  private final HttpStatus httpStatus;
}
