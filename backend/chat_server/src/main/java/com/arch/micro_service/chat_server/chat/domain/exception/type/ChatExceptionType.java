package com.arch.micro_service.chat_server.chat.domain.exception.type;

import com.arch.micro_service.chat_server.shared.domain.exception.BaseExceptionType;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatExceptionType implements BaseExceptionType {

  CHAT_NOT_FOUND("Chat not found", HttpStatus.NOT_FOUND);

  private final String message;

  private final HttpStatus httpStatus;

}
