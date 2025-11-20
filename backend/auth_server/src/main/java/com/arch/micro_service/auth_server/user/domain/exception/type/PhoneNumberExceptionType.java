package com.arch.micro_service.auth_server.user.domain.exception.type;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneNumberExceptionType implements BaseExceptionType {

  EMPTY_PHONE("Phone number cannot be null or empty."),
  TOO_SHORT("Phone number is too short."),
  TOO_LONG("Phone number cannot exceed 16 digits."),
  INVALID_CHARACTERS("Phone number contains invalid characters."),
  INVALID_COUNTRY_CODE("Phone number has an invalid country code."),
  INVALID_PHONE_NUMBER("Invalid phone number.");

  private String message;

}
