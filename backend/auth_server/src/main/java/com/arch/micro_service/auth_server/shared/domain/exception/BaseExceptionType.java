package com.arch.micro_service.auth_server.shared.domain.exception;

/**
 * Implemented by all Exception Types
 */
public interface BaseExceptionType {

  /**
   * @return the code exception message
   */
  String getMessage();

  /**
   * @return the name of the exception
   */
  String name();
}
