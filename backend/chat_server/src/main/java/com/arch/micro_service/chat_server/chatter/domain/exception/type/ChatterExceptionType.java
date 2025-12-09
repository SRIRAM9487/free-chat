package com.arch.micro_service.chat_server.chatter.domain.exception.type;

import com.arch.micro_service.chat_server.shared.domain.exception.BaseExceptionType;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatterExceptionType implements BaseExceptionType {

  CHATTER_NOT_FOUND("Chatter not found", HttpStatus.NOT_FOUND),
  UNIQUE_USER_ID("User id already exists", HttpStatus.CONFLICT);

  private final String message;

  private final HttpStatus httpStatus;
}
