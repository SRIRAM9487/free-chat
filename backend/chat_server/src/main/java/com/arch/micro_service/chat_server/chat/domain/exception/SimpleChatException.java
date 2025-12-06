package com.arch.micro_service.chat_server.chat.domain.exception;

import com.arch.micro_service.chat_server.chat.domain.exception.type.SimpleChatExceptionType;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseException;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseExceptionType;

public class SimpleChatException extends BaseException {

  public SimpleChatException(BaseExceptionType type) {
    super(type);
  }

  public static SimpleChatException notFound() {
    return new SimpleChatException(SimpleChatExceptionType.SIMPLE_CHAT_NOT_FOUND);
  }

}
