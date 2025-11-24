package com.arch.micro_service.auth_server.security.web.filter;

import java.io.IOException;

import com.arch.micro_service.auth_server.user.application.service.impl.JwtServiceImpl;
import com.arch.micro_service.auth_server.user.application.service.impl.UserDetailsServiceImpl;
import com.arch.micro_service.auth_server.user.domain.entity.UserImpl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private final UserDetailsServiceImpl userDetailsServiceImp;
  private final JwtServiceImpl jwtService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    log.debug("Invoking custom Jwt Authentication Filter");

    String path = request.getRequestURI();
    log.trace("Request Path : {}", path);

    String authHeader = request.getHeader("Authorization");
    String token = null;
    String userName = null;

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      log.error("Authorization Header is present");
      token = authHeader.substring(7);
      userName = jwtService.extractUserId(token);
    }

    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      log.trace("User is authenticated");
      UserImpl user = (UserImpl) userDetailsServiceImp.loadUserByUsername(userName);
      if (jwtService.validate(token, user.getId())) {
        log.trace("User is authenticated");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
            user.getAuthorities());
        log.trace("userName Password Authentication Token is set");
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.trace("Security Context is set");
      }
    }

    log.trace(" Requested User id : {}", userName);

    filterChain.doFilter(request, response);
  }

}
