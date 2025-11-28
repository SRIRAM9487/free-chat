package com.arch.micro_service.auth_server.user.application.service;

import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserLoginRequest;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserLoginResponse;

public interface UserLoginService {

  UserLoginResponse login(UserLoginRequest request);

  void userVerify(String userId);

  void resetPassword(String userId);

  void newPassword(String token, UserLoginRequest userLoginRequest);

}
