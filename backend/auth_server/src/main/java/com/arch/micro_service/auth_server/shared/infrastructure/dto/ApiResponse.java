package com.arch.micro_service.auth_server.shared.infrastructure.dto;

import java.time.LocalDateTime;

public record ApiResponse<T>(
    boolean success,
    T data,
    LocalDateTime timeStamp) {

  public static <T> ApiResponse<T> create(T data) {
    return new ApiResponse<>(true, data, LocalDateTime.now());
  }

}
