package com.arch.micro_service.auth_server.security.web.filter;

import java.io.IOException;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ExceptionalHandlerFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (ExpiredJwtException e) {
      log.warn("Token is expired: {}", e.getMessage());
      writeException("Token Expired", response, request);
    } catch (MalformedJwtException e) {
      log.warn("Malformed JWT token: {}", e.getMessage());
      writeException("Malformed Token", response, request);
    } catch (SecurityException e) {
      log.warn("Signature validation failed: {}", e.getMessage());
      writeException("Signature validation failed", response, request);
    } catch (UnsupportedJwtException e) {
      log.warn("Unsupported JWT token: {}", e.getMessage());
      writeException("Unsupported jwt", response, request);
    } catch (IllegalArgumentException e) {
      log.warn("Empty or null JWT token: {}", e.getMessage());
      writeException("Null token", response, request);
    } catch (JwtException e) {
      log.warn("General JWT error: {}", e.getMessage());
      writeException("Invalid Token", response, request);
    } catch (NullPointerException e) {
      log.error("Missing required claim: {}", e.getMessage());
      writeException("Missing claims", response, request);
    }
  }

  public void writeException(String value, HttpServletResponse response, HttpServletRequest request)
      throws StreamWriteException, DatabindException, IOException {
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    new ObjectMapper().writeValue(response.getWriter(), ApiException.jwt(value, request));
  }

}
