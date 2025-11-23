package com.arch.micro_service.auth_server.user.domain.exception.type;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PhoneNumberExceptionType implements BaseExceptionType {

  EMPTY_PHONE("Phone number cannot be null or empty", HttpStatus.BAD_REQUEST),
  TOO_SHORT("Phone number is too short", HttpStatus.BAD_REQUEST),
  TOO_LONG("Phone number cannot exceed 16 digits", HttpStatus.BAD_REQUEST),
  INVALID_CHARACTERS("Phone number contains invalid characters", HttpStatus.BAD_REQUEST),
  INVALID_COUNTRY_CODE("Phone number has an invalid country code", HttpStatus.BAD_REQUEST),
  INVALID_PHONE_NUMBER("Invalid phone number", HttpStatus.BAD_REQUEST);

  private final String message;
  private final HttpStatus httpStatus;
}
