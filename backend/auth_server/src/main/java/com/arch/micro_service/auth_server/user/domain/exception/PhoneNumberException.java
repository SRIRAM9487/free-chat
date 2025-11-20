package com.arch.micro_service.auth_server.user.domain.exception;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseException;
import com.arch.micro_service.auth_server.user.domain.exception.type.PhoneNumberExceptionType;

/**
 * Exception to handle {@link PhoneNumbe}
 * 
 * @author SRIRAM
 */
public class PhoneNumberException extends BaseException {

  public PhoneNumberException(PhoneNumberExceptionType type) {
    super(type.getMessage(), type.name());
  }

  public static PhoneNumberException empty() {
    return new PhoneNumberException(PhoneNumberExceptionType.EMPTY_PHONE);
  }

  public static PhoneNumberException tooShort() {
    return new PhoneNumberException(PhoneNumberExceptionType.TOO_SHORT);
  }

  public static PhoneNumberException tooLong() {
    return new PhoneNumberException(PhoneNumberExceptionType.TOO_LONG);
  }

  public static PhoneNumberException invalidCharacters() {
    return new PhoneNumberException(PhoneNumberExceptionType.INVALID_CHARACTERS);
  }

  public static PhoneNumberException invalidCountryCode() {
    return new PhoneNumberException(PhoneNumberExceptionType.INVALID_COUNTRY_CODE);
  }

  public static PhoneNumberException invalidPhoneNumber() {
    return new PhoneNumberException(PhoneNumberExceptionType.INVALID_PHONE_NUMBER);
  }
}
