package com.arch.micro_service.chat_server.chatgroup.infrastructure.exception;

import com.arch.micro_service.chat_server.chatgroup.domain.exception.ChatGroupException;
import com.arch.micro_service.chat_server.shared.infrastructure.dto.api.ApiException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ChatGroupExceptionAdvice {

  @ExceptionHandler(ChatGroupException.class)
  public ResponseEntity<ApiException> handlChatGroupException(
      ChatGroupException exception,
      HttpServletRequest request) {
    var res = ApiException.create(exception, request);
    return ResponseEntity.status(exception.getHttpStatus()).body(res);

  }

}
