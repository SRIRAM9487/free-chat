package com.arch.micro_service.chat_server.shared.domain;

import com.arch.micro_service.chat_server.shared.domain.exception.BaseException;
import com.arch.micro_service.chat_server.shared.domain.exception.BaseExceptionType;
import com.arch.micro_service.chat_server.shared.domain.exception.CustomDataIntegrityExceptionType;

public class CustomDataIntegrityException extends BaseException {

  public CustomDataIntegrityException(BaseExceptionType type) {
    super(type);
  }

  public static CustomDataIntegrityException uniqueContraint() {
    return new CustomDataIntegrityException(CustomDataIntegrityExceptionType.UNIQUE_CONSTRAINT);
  }

  public static CustomDataIntegrityException uniqueUserId() {
    return new CustomDataIntegrityException(CustomDataIntegrityExceptionType.CHATTER_UNIQUE_USER_ID);
  }

}
