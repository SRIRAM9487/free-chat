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

  public static UserException notFound() {
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

}
