package com.arch.micro_service.chat_server.shared.infrastructure.dto.api;

import java.time.LocalDateTime;

import com.arch.micro_service.chat_server.shared.domain.exception.BaseException;

import org.springframework.http.HttpStatus;

import jakarta.servlet.http.HttpServletRequest;

public record ApiException(
    boolean success,
    String method,
    HttpStatus status,
    String path,
    String message,
    String code,
    String timeStamp) {

  public static ApiException create(BaseException exception, HttpServletRequest request) {
    return new ApiException(
        false,
        request.getMethod(),
        exception.getHttpStatus(),
        request.getPathInfo(),
        exception.getMessage(),
        exception.getCode(),
        LocalDateTime.now().toString());
  }

}
