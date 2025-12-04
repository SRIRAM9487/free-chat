package com.arch.micro_service.auth_server.security.handler;

import com.arch.micro_service.auth_server.log.LoggerContext;
import com.arch.micro_service.auth_server.log.LoggerContextHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogHandler implements HandlerInterceptor {

  private final Logger log = LoggerFactory.getLogger(LogHandler.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    log.trace("Logging context set");

    String ip = request.getHeader("X-Forwarded-For");

    if (ip == null) {
      ip = request.getRemoteAddr();
    }

    LoggerContext context = new LoggerContext(
        request.getRequestURI(),
        request.getMethod(),
        System.currentTimeMillis(),
        ip,
        request.getHeader("User-Agent"));

    LoggerContextHolder.set(context);
    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    log.trace("Logging context cleared");
    LoggerContextHolder.clear();
  }
}
