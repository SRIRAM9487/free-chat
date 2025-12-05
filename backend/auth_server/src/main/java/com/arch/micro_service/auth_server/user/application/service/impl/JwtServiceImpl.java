package com.arch.micro_service.auth_server.user.application.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import com.arch.micro_service.auth_server.user.application.service.JwtService;
import com.arch.micro_service.auth_server.user.domain.entity.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

  @Value("${jwt.secret}")
  private String secretKey;

  private SecretKey getKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    final Claims claims = extractAllClaims(token);
    return claimResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
        .verifyWith(getKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  @Override
  public String generate(User user) {
    return this.generate(user.getId());
  }

  @Override
  public String generate(Long userId) {

    Map<String, Object> claims = new HashMap<>();
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime limit = now.toLocalDate().plusDays(100).atStartOfDay();
    Date issuedAt = new Date();
    Date expiration = Date.from(limit.atZone(ZoneId.systemDefault()).toInstant());

    return Jwts
        .builder()
        .claims()
        .add(claims)
        .subject(userId.toString())
        .issuedAt(issuedAt)
        .expiration(expiration)
        .and()
        .signWith(getKey())
        .compact();
  }

  @Override
  public boolean validate(String token, Long userId) {
    String tokenUserId = extractUserId(token);
    return (tokenUserId.equals(userId.toString()) && !isTokenExpired(token));
  }

  @Override
  public String extractUserId(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  @Override
  public boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

}
