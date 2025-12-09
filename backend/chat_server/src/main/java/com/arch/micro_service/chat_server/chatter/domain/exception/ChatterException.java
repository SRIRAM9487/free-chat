package com.arch.micro_service.chat_server.chatter.domain.exception;

import com.arch.micro_service.chat_server.chatter.domain.exception.type.ChatterExceptionType;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseException;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseExceptionType;

public class ChatterException extends BaseException {

  public ChatterException(BaseExceptionType type) {
    super(type);
  }

  public static ChatterException notFound() {
    return new ChatterException(ChatterExceptionType.CHATTER_NOT_FOUND);
  }

  public static ChatterException uniqueUserId() {
    return new ChatterException(ChatterExceptionType.UNIQUE_USER_ID);
  }

}
