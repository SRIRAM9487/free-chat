package com.arch.micro_service.chat_server.chatgroup.domain.exception;

import com.arch.micro_service.chat_server.chatgroup.domain.exception.type.GroupMemberExceptionType;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseException;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseExceptionType;

public class GroupMemberException extends BaseException {

  public GroupMemberException(BaseExceptionType type) {
    super(type);
  }

  public static GroupMemberException notFound() {
    return new GroupMemberException(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND);
  }

  public static GroupMemberException chatterIdNotFound() {
    return new GroupMemberException(GroupMemberExceptionType.CHATTER_ID_NOT_FOUND);
  }

  public static GroupMemberException groupIdNotFound() {
    return new GroupMemberException(GroupMemberExceptionType.GROUP_ID_NOT_FOUND);
  }

}
