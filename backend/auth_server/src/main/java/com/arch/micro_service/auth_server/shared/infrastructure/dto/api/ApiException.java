package com.arch.micro_service.auth_server.shared.infrastructure.dto.api;

import java.time.LocalDateTime;

import com.arch.micro_service.auth_server.shared.domain.exception.BaseException;

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

  public static ApiException badRequest(BaseException exception, HttpServletRequest request) {
    return create(exception, HttpStatus.BAD_REQUEST, request);
  }

  public static ApiException unAuthorized(BaseException exception, HttpServletRequest request) {
    return create(exception, HttpStatus.UNAUTHORIZED, request);
  }

  public static ApiException unAuthenticated(BaseException exception, HttpServletRequest request) {
    return create(exception, HttpStatus.BAD_REQUEST, request);
  }

  public static ApiException forbidden(BaseException exception, HttpServletRequest request) {
    return create(exception, HttpStatus.FORBIDDEN, request);
  }

  public static ApiException notFound(BaseException exception, HttpServletRequest request) {
    return create(exception, HttpStatus.NOT_FOUND, request);
  }

  public static ApiException conflict(BaseException exception, HttpServletRequest request) {
    return create(exception, HttpStatus.CONFLICT, request);
  }

  public static ApiException badRequest(String message, String code, HttpServletRequest request) {
    return create(HttpStatus.BAD_REQUEST, message, code, request);
  }

  public static ApiException unAuthorized(String message, String code, HttpServletRequest request) {
    return create(HttpStatus.UNAUTHORIZED, message, code, request);
  }

  public static ApiException unAuthenticated(String message, String code, HttpServletRequest request) {
    return create(HttpStatus.BAD_REQUEST, message, code, request);
  }

  public static ApiException forbidden(String message, String code, HttpServletRequest request) {
    return create(HttpStatus.FORBIDDEN, message, code, request);
  }

  public static ApiException notFound(String message, String code, HttpServletRequest request) {
    return create(HttpStatus.NOT_FOUND, message, code, request);
  }

  public static ApiException conflict(String message, String code, HttpServletRequest request) {
    return create(HttpStatus.CONFLICT, message, code, request);
  }

  public static ApiException jwt(String message, HttpServletRequest request) {
    return create(HttpStatus.UNAUTHORIZED, message, "Invalid jwt", request);
  }

}
