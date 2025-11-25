package com.arch.micro_service.auth_server.user.domain.exception;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseException;
import com.arch.micro_service.auth_server.user.domain.exception.type.PhoneNumberExceptionType;

/**
 * Exception to handle {@link com.arch.micro_service.auth_server.user.domain.vo.PhoneNumber}
 * 
 * @author SRIRAM
 */
public class PhoneNumberException extends BaseException {

  public PhoneNumberException(PhoneNumberExceptionType type) {
    super(type);
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

}
