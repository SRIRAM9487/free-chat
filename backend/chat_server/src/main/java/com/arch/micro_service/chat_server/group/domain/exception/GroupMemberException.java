package com.arch.micro_service.chat_server.group.domain.exception;

import com.arch.micro_service.chat_server.group.domain.exception.type.GroupMemberExceptionType;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseException;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseExceptionType;

public class GroupMemberException extends BaseException {

  public GroupMemberException(BaseExceptionType type) {
    super(type);
  }

  public static GroupMemberException notFound() {
    return new GroupMemberException(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND);
  }

}
