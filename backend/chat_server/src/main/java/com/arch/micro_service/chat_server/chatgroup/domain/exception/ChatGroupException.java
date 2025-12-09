package com.arch.micro_service.chat_server.chatgroup.domain.exception;

import com.arch.micro_service.chat_server.chatgroup.domain.exception.type.ChatGroupExceptionType;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseException;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseExceptionType;

public class ChatGroupException extends BaseException {

  public ChatGroupException(BaseExceptionType type) {
    super(type);
  }

  public static ChatGroupException notFound() {
    return new ChatGroupException(ChatGroupExceptionType.GROUP_NOT_FOUND);
  }

}
