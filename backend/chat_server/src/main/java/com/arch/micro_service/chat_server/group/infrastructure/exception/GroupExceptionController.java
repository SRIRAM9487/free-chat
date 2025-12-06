package com.arch.micro_service.chat_server.group.infrastructure.exception;

import com.arch.micro_service.chat_server.group.domain.exception.GroupException;
import com.arch.micro_service.chat_server.shared.infrastructure.dto.api.ApiException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GroupExceptionController {

  @ExceptionHandler(GroupException.class)
  public ResponseEntity<ApiException> handleGroupException(GroupException exception,
      HttpServletRequest httpServletRequest) {
    return ResponseEntity.status(exception.getHttpStatus()).body(ApiException.create(exception, httpServletRequest));
  }

}
