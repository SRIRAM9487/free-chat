package com.arch.micro_service.auth_server.user.application.service;

public interface UserEmailService {

  void sendVerification(String id);

  void verifyEmail(String email, String token);

}
