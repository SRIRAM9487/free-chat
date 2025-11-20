package com.arch.micro_service.auth_server.user.domain.exception;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseException;
import com.arch.micro_service.auth_server.user.domain.exception.type.PasswordExceptionType;

/**
 * Exception to handle Password
 * 
 * @author SRIRAM
 */
public class PasswordException extends BaseException {

  public PasswordException(PasswordExceptionType type) {
    super(type.getMessage(), type.name());
  }

  public static PasswordException empty() {
    return new PasswordException(PasswordExceptionType.NULL_PASSWORD);
  }

  public static PasswordException requireAtleastOneLowerCaseLetter() {
    return new PasswordException(PasswordExceptionType.ATLEAST_ONE_LOWERCASE_LETTER);
  }

  public static PasswordException requireAtleastOneCapitalLetter() {
    return new PasswordException(PasswordExceptionType.ATLEAST_ONE_CAPITAL_LETTER);
  }

  public static PasswordException requireAtleastOneNumber() {
    return new PasswordException(PasswordExceptionType.ATLEAST_ONE_NUMBER);
  }

  public static PasswordException requireAtleastEightCharacter() {
    return new PasswordException(PasswordExceptionType.ATLEAST_EIGHT_CHARACTERS);
  }

  public static PasswordException notValidToken() {
    return new PasswordException(PasswordExceptionType.NOT_VALID_TOKEN);
  }
}
