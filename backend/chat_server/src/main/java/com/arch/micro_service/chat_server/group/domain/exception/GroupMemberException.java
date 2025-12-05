package com.arch.micro_service.chat_server.group.domain.exception;

import com.arch.micro_service.chat_server.shared.domain.exception.BaseException;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseExceptionType;

public class GroupMemberException extends BaseException {

  public GroupMemberException(BaseExceptionType type) {
    super(type);
  }

}
