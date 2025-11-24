package com.arch.micro_service.auth_server.security.constant;

/**
 * CONTAINS PUBLIC URLS
 */
public class PathConstant {

  public static String[] GET_PATHS = {
      "/actuator/**",
      "/v1/user/email/verify",
      "/error/**",
      "/errors/**",
      "/v1/user/test"
  };

  public static String[] POST_PATHS = {
      "/v1/user/login",
      "/v1/user/file/upload"
  };

}
