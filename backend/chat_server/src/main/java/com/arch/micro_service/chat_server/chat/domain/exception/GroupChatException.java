package com.arch.micro_service.chat_server.chat.domain.exception;

import com.arch.micro_service.chat_server.chat.domain.exception.type.GroupChatExceptionType;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseException;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseExceptionType;

public class GroupChatException extends BaseException {

  public GroupChatException(BaseExceptionType type) {
    super(type);
  }

  public static GroupChatException notFound() {
    return new GroupChatException(GroupChatExceptionType.CHAT_NOT_FOUND);
  }

}
