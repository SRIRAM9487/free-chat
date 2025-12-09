package com.arch.micro_service.chat_server.logger.handler;

import com.arch.micro_service.chat_server.logger.context.MetaContext;
import com.arch.micro_service.chat_server.logger.context.MetaContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MetaHandler implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String ip = request.getHeader("X-Forwarded-For");
    if (ip == null) {
      ip = request.getRemoteAddr();
    }
    String userId = getCurrentUser();
    MetaContext context = new MetaContext(
        userId,
        request.getRequestURI(),
        request.getMethod(),
        System.currentTimeMillis(),
        ip,
        request.getHeader("User-Agent"));
    MetaContextHolder.set(context);
    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    MetaContextHolder.clear();
  }

  private final String getCurrentUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null && auth.getPrincipal() instanceof UserDetails user) {
      return user.getUsername().toString();
    }
    return "anonymous";
  }

}
