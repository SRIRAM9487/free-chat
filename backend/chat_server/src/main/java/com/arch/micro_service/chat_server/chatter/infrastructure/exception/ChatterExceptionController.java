package com.arch.micro_service.chat_server.chatter.infrastructure.exception;

import com.arch.micro_service.chat_server.chatter.domain.exception.ChatterException;
import com.arch.micro_service.chat_server.shared.infrastructure.dto.api.ApiException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ChatterExceptionController {

  @ExceptionHandler(ChatterException.class)
  public ResponseEntity<ApiException> handleChatterException(ChatterException exception, HttpServletRequest request) {
    var response = ApiException.create(exception, request);
    return ResponseEntity.status(exception.getHttpStatus()).body(response);
  }

}
