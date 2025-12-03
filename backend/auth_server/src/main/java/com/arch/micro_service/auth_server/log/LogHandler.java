package com.arch.micro_service.auth_server.log;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogHandler implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

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
    LoggerContextHolder.clear();
  }
}
