package com.arch.micro_service.chat_server.chat.domain.exception;

import com.arch.micro_service.chat_server.shared.domain.exception.BaseException;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseExceptionType;

public class GroupChatException extends BaseException {

  public GroupChatException(BaseExceptionType type) {
    super(type);
  }

}
