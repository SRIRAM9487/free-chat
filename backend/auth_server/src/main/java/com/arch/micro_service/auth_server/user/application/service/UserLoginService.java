package com.arch.micro_service.auth_server.user.application.service;

import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserLoginRequest;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserLoginResponse;

public interface UserLoginService {

  UserLoginResponse login(UserLoginRequest request);

  void resetPassword(String userId);

  void newPassword(String email, String token, String password);

}
