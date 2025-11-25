package com.arch.micro_service.auth_server.user.application.service.impl;

import com.arch.micro_service.auth_server.user.application.service.JwtService;
import com.arch.micro_service.auth_server.user.application.service.UserAuthService;
import com.arch.micro_service.auth_server.user.application.usecase.UserFindUseCase;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.exception.UserException;
import com.arch.micro_service.auth_server.user.infrastructure.dto.mapper.UserMapper;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserLoginRequest;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserLoginResponse;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserPasswordVerificationResponse;
import com.arch.micro_service.auth_server.user.infrastructure.persistence.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {

  private final UserFindUseCase userFindUseCase;
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Override
  public UserLoginResponse login(UserLoginRequest request) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.userId(), request.password()));

    if (!authentication.isAuthenticated())
      throw UserException.authenticationFailed(request.userId());

    User user = userFindUseCase.findByUserId(request.userId());

    if (!user.isVerified())
      throw UserException.emailNotVerified();

    String jwt = jwtService.generate(user);

    return userMapper.toUserLoginResponse(user, jwt);
  }

  // TODO:
  @Override
  public UserPasswordVerificationResponse userVerify(String userId) {
    throw new UnsupportedOperationException("Unimplemented method 'userVerify'");
  }

  // TODO:
  @Override
  public UserPasswordVerificationResponse resetPassword(String userId) {

    User user = userFindUseCase.findByUserId(userId);

    user.resetPassword();

    userRepository.save(user);

    return userMapper.toUserPasswordVerificationResponse(true,
        "Password has been successfully reset. A confirmation email has been sent to the user.");
  }

  // TODO:
  @Override
  public UserPasswordVerificationResponse newPassword(String token, UserLoginRequest userLoginRequest) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'newPassword'");
  }

}
