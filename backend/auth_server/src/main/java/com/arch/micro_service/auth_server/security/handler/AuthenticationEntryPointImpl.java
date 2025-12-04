package com.arch.micro_service.auth_server.security.handler;

import java.io.IOException;

import com.arch.micro_service.auth_server.security.exception.UnAuthenticatedException;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

  private final Logger log = LoggerFactory.getLogger("MethodLogger");

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
    log.trace("Authentication Failed for URI: {}", request.getRequestURI());
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.setContentType("application/json");
    ApiException exception = ApiException.unAuthorized(UnAuthenticatedException.authenticationFailed(), request);
    new ObjectMapper().writeValue(response.getWriter(), exception);
  }

}
