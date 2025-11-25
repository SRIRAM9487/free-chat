package com.arch.micro_service.auth_server.user.domain.exception;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseException;
import com.arch.micro_service.auth_server.user.domain.exception.type.EmailExceptionType;

/**
 * Exception to handle Email
 * 
 * @author SRIRAM
 */
public class EmailException extends BaseException {

  public EmailException(EmailExceptionType type) {
    super(type);
  }

  public static EmailException empty() {
    return new EmailException(EmailExceptionType.NULL_EMAIL);
  }

  public static EmailException missingAtSymbol() {
    return new EmailException(EmailExceptionType.MISSING_AT_SYMBOL);
  }

  public static EmailException multipleAtSymbols() {
    return new EmailException(EmailExceptionType.MULTIPLE_AT_SYMBOLS);
  }

  public static EmailException emptyLocalPart() {
    return new EmailException(EmailExceptionType.EMPTY_LOCAL_PART);
  }

  public static EmailException emptyDomainPart() {
    return new EmailException(EmailExceptionType.EMPTY_DOMAIN_PART);
  }

  public static EmailException tokenVerificationFailed(String email) {
    return new EmailException(EmailExceptionType.INVALID_EMAIL_TOKEN);
  }

  public static EmailException uniqueKeyViolation(String email) {
    return new EmailException(EmailExceptionType.UNIQUE_EMAIL);
  }
}
