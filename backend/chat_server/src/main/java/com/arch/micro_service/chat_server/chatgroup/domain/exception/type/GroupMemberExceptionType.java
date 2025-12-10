package com.arch.micro_service.chat_server.chatgroup.domain.exception.type;

import com.arch.micro_service.chat_server.shared.domain.exception.BaseExceptionType;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupMemberExceptionType implements BaseExceptionType {

  CHATTER_ID_NOT_FOUND("Chatter id not found", HttpStatus.NOT_FOUND),
  GROUP_ID_NOT_FOUND("Group id not found", HttpStatus.NOT_FOUND),
  GROUP_MEMEBER_NOT_FOUND("Group member not found", HttpStatus.NOT_FOUND);

  private final String message;

  private final HttpStatus httpStatus;
}
