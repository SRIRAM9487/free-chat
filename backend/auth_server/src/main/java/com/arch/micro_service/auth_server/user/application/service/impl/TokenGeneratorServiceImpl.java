package com.arch.micro_service.auth_server.user.application.service.impl;

import java.util.UUID;

import com.arch.micro_service.auth_server.user.application.service.TokenGeneratorService;

import org.springframework.stereotype.Service;

@Service
public class TokenGeneratorServiceImpl implements TokenGeneratorService {

  @Override
  public String generateToken() {
    String token = UUID.randomUUID().toString().replace("-", "") + UUID.randomUUID().toString().replace("-", "");
    return token;
  }

}
