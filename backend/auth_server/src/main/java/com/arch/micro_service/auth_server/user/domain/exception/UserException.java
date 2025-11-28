package com.arch.micro_service.auth_server.user.domain.exception;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseException;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.exception.type.UserExceptionType;

/**
 * Exception to handle {@link User}
 * 
 * @author SRIRAM
 */
public class UserException extends BaseException {

  public UserException(UserExceptionType type) {
    super(type);
  }

  public static UserException notFound(String id) {
    return new UserException(UserExceptionType.USER_NOT_FOUND);
  }

  public static UserException authenticationFailed(String id) {
    return new UserException(UserExceptionType.AUTHENTICATION_FAILED);
  }

  public static UserException emailNotVerified() {
    return new UserException(UserExceptionType.EMAIL_NOT_VERIFIED);
  }

  public static UserException emailAlreadyVerified() {
    return new UserException(UserExceptionType.EMAIL_NOT_VERIFIED);
  }

  public static UserException invalidOtp() {
    return new UserException(UserExceptionType.INVALID_OTP);
  }

  public static UserException invalidPasswordVerificationToken() {
    return new UserException(UserExceptionType.INVALID_PASSWORD_VERIFICATION_TOKEN);
  }

  public static UserException emailNotUniqueViolation() {
    return new UserException(UserExceptionType.USER_EMAIL_MUST_BE_UNIQUE);
  }

  public static UserException userNameNotUniqueViolation() {
    return new UserException(UserExceptionType.USER_NAME_MUST_BE_UNIQUE);
  }

}
