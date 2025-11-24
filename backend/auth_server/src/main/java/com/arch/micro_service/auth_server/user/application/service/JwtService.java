package com.arch.micro_service.auth_server.user.application.service;

import com.arch.micro_service.auth_server.user.application.service.impl.JwtServiceImpl;
import com.arch.micro_service.auth_server.user.domain.entity.User;

/**
 * Jwt token utils interface
 * implemented by {@link JwtServiceImpl}
 */
public interface JwtService {

  /**
   * generate jwt token for the {@link User}
   */
  String generate(User user);

  /**
   * generate jwt token for the given userId
   */
  String generate(Long userId);

  /**
   * check if the jwt token is valid
   */
  boolean validate(String token, Long userId);

  /**
   * extract userId from the token
   */
  String extractUserId(String token);

  /**
   * is the token expired or not
   */
  boolean isTokenExpired(String token);

}
