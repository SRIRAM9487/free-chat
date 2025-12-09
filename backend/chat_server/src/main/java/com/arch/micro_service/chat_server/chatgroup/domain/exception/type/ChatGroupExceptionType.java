package com.arch.micro_service.chat_server.chatgroup.domain.exception.type;

import com.arch.micro_service.chat_server.shared.domain.exception.BaseExceptionType;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatGroupExceptionType implements BaseExceptionType {

  GROUP_NOT_FOUND("Group not found", HttpStatus.NOT_FOUND);

  private final String message;

  private final HttpStatus httpStatus;

}
