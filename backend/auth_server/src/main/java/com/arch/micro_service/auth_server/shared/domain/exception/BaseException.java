package com.arch.micro_service.auth_server.shared.domain.exception;

import java.time.LocalDateTime;

import lombok.Getter;

/*
 * Implemented by all Exceptions 
 * for centralized and easy exception handling
 */
@Getter
public abstract class BaseException extends RuntimeException {

  /*
   * When the exception happened
   */
  private final LocalDateTime exceptionTime;

  /*
   * Custom Exception code
   */
  private final String code;

  /**
   * Base constructor
   * 
   * @param message exception detail
   * @param code    exception code
   */
  public BaseException(String message, String code) {
    super(message);
    this.code = code;
    this.exceptionTime = LocalDateTime.now();
  }

  /**
   * Type constructor
   * 
   * @param message exception detail
   * @param code    exception code
   */
  public BaseException(BaseExceptionType type) {
    super(type.getMessage());
    this.code = type.name();
    this.exceptionTime = LocalDateTime.now();
  }

}
