package com.arch.micro_service.auth_server.security.handler;

import java.io.IOException;

import com.arch.micro_service.auth_server.security.exception.UnAuthorizedException;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

  private final Logger log = LoggerFactory.getLogger("MethodLogger");

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.setContentType("application/json");
    log.trace("Access denied for URI: {}", request.getRequestURI());
    ApiException exception = ApiException.unAuthorized(UnAuthorizedException.accessDenied(), request);
    new ObjectMapper().writeValue(response.getWriter(), exception);
  }

}
