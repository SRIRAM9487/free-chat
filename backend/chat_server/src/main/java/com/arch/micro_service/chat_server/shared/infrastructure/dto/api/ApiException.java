package com.arch.micro_service.chat_server.shared.infrastructure.dto.api;

import java.time.LocalDateTime;

import com.arch.micro_service.chat_server.shared.domain.exception.BaseException;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import jakarta.servlet.http.HttpServletRequest;

public record ApiException(
    boolean success,
    String method,
    HttpStatus status,
    String timeStamp,
    String path,
    String message,
    String code) {

  public static ApiException create(HttpMethod method, HttpStatus status, String message, String code,
      HttpServletRequest request) {
    return new ApiException(
        false,
        method.name(),
        status,
        LocalDateTime.now().toString(),
        request.getPathInfo(),
        message,
        code);
  }

  public static ApiException create(HttpStatus status, String message, String code, HttpServletRequest request) {
    return new ApiException(
        false,
        request.getMethod(),
        status,
        LocalDateTime.now().toString(),
        request.getPathInfo(),
        message,
        code);
  }

  public static ApiException create(BaseException exception, HttpServletRequest request) {
    return new ApiException(
        false,
        request.getMethod(),
        null,
        LocalDateTime.now().toString(),
        request.getPathInfo(),
        exception.getMessage(),
        exception.getCode());
  }

  public static ApiException create(BaseException exception, HttpStatus status, HttpServletRequest request) {
    return new ApiException(
        false,
        request.getMethod(),
        status,
        LocalDateTime.now().toString(),
        request.getPathInfo(),
        exception.getMessage(),
        exception.getCode());
  }

  public static ApiException unAuthorized(BaseException exception, HttpServletRequest request) {
    return create(exception, HttpStatus.UNAUTHORIZED, request);
  }

  public static ApiException conflict(BaseException exception, HttpServletRequest request) {
    return create(exception, HttpStatus.CONFLICT, request);
  }

  public static ApiException notFound(BaseException exception, HttpServletRequest request) {
    return create(HttpStatus.NOT_FOUND, exception.getMessage(), exception.getCode(), request);
  }

  public static ApiException jwt(String message, HttpServletRequest request) {
    return create(HttpStatus.UNAUTHORIZED, message, "Invalid jwt", request);
  }

}
