package com.arch.micro_service.chat_server.shared.domain.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomDataIntegrityExceptionType implements BaseExceptionType {

  UNIQUE_CONSTRAINT("Value must be unique", HttpStatus.CONFLICT),
  CHATTER_UNIQUE_USER_ID("Chatter user id must be unique", HttpStatus.CONFLICT);

  private final String message;

  private final HttpStatus httpStatus;

}
