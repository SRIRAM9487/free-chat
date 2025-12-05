package com.arch.micro_service.chat_server.group.domain.exception.type;

import com.arch.micro_service.chat_server.shared.domain.exception.BaseExceptionType;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupMemberExceptionType implements BaseExceptionType {

  GROUP_MEMEBER_NOT_FOUND("Group member not found", HttpStatus.NOT_FOUND);

  private final String message;

  private final HttpStatus httpStatus;
}
