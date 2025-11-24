package com.arch.micro_service.auth_server.user.application.service;

import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserLoginRequest;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserLoginResponse;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserPasswordVerificationResponse;

public interface UserAuthService {

  UserLoginResponse login(UserLoginRequest request);

  UserPasswordVerificationResponse userVerify(String userId);

  UserPasswordVerificationResponse resetPassword(String userId);

  UserPasswordVerificationResponse newPassword(String token, UserLoginRequest userLoginRequest);

}
