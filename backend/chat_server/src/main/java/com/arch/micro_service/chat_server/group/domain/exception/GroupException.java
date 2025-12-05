package com.arch.micro_service.chat_server.group.domain.exception;

import com.arch.micro_service.chat_server.shared.domain.exception.BaseException;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseExceptionType;

public class GroupException extends BaseException {

  public GroupException(BaseExceptionType type) {
    super(type);
  }

}
