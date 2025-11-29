package com.arch.micro_service.gateway_server.constant;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AppConstant {

  public static final List<String> PUBLIC_URL = List.of(
      "/auth/user/login",
      "/auth/user/");

  public static final List<String> ALLOWED_CORS_METHODS = List.of(
      "GET",
      "POST",
      "PUT",
      "DELETE",
      "OPTIONS",
      "PATCH");

  public static final String ALLOWED_CORS_PATTERN = "*";
  public static final String ALLOWED_CORS_URL = "http://172.18.0.1:5173";

  public static final List<String> ALLOWED_CORS_HEADERS = List.of("*");

}
