package com.arch.micro_service.auth_server.user.domain.exception.type;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserExceptionType implements BaseExceptionType {

  USER_NOT_FOUND("User not found"),
  AUTHENTICATION_FAILED("Authentication failed"),
  EMAIL_NOT_VERIFIED("Email not verified"),
  INVALID_OTP("Invalid OTP"),
  USER_NAME_NOT_FOUND("User name not found");

  private final String message;

}
