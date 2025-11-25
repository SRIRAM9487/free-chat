package com.arch.micro_service.auth_server.security.exception.type;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseExceptionType;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomNoHandlerFoundExceptionType implements BaseExceptionType {

  NO_URI_FOUND("No url match found", HttpStatus.NOT_FOUND);

  private final String message;
  private final HttpStatus httpStatus;

}
