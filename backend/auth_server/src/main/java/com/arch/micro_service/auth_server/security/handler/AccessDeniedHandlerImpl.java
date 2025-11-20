package com.arch.micro_service.auth_server.security.handler;

import java.io.IOException;

import com.arch.micro_service.auth_server.security.exception.UnAuthorizedException;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    log.trace("Access Denied");
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.setContentType("application/json");
    ApiException exception = ApiException.unAuthorized(UnAuthorizedException.accessDenied(), request);
    new ObjectMapper().writeValue(response.getWriter(), exception);
  }

}
