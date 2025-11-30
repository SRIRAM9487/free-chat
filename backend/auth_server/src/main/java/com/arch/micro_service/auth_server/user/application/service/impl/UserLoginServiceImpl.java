package com.arch.micro_service.auth_server.user.application.service.impl;

import com.arch.micro_service.auth_server.message.infrastructure.event.PasswordResetEvent;
import com.arch.micro_service.auth_server.message.infrastructure.publisher.EmailEventPublisher;
import com.arch.micro_service.auth_server.user.application.service.CacheService;
import com.arch.micro_service.auth_server.user.application.service.JwtService;
import com.arch.micro_service.auth_server.user.application.service.TokenGeneratorService;
import com.arch.micro_service.auth_server.user.application.service.UserLoginService;
import com.arch.micro_service.auth_server.user.application.usecase.UserFindUseCase;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.exception.UserException;
import com.arch.micro_service.auth_server.user.infrastructure.dto.mapper.UserMapper;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserLoginRequest;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserLoginResponse;
import com.arch.micro_service.auth_server.user.infrastructure.persistence.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {

  private final UserFindUseCase userFindUseCase;
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final CacheService cacheService;
  private final PasswordEncoder passwordEncoder;
  private final TokenGeneratorService tokenGeneratorService;
  private final EmailEventPublisher emailEventPublisher;

  @Override
  public UserLoginResponse login(UserLoginRequest request) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.userId(), request.password()));

    if (!authentication.isAuthenticated()) {
      log.trace("User verification failed");
      throw UserException.authenticationFailed(request.userId());
    }

    User user = userFindUseCase.findByUserId(request.userId());

    if (!user.isVerified()) {
      throw UserException.emailNotVerified();
    }

    String jwt = jwtService.generate(user);

    return userMapper.toUserLoginResponse(user, jwt);
  }

  @Override
  public void resetPassword(String userId) {

    User user = userFindUseCase.findByUserId(userId);

    user.resetPassword();
    String token = tokenGeneratorService.generateToken();

    cacheService.save(user.getEmail().value(), token);

    userRepository.save(user);
    log.trace("User {} password reset", userId);

    emailEventPublisher.publishPasswordResetEmail(new PasswordResetEvent(user.getEmail().value(), userId, token));

  }

  @Override
  public void newPassword(String email, String token, String password) {

    User user = userFindUseCase.findByUserId(email);

    if (!cacheService.retrive(email).equals(token)) {
      throw UserException.invalidPasswordVerificationToken();
    }

    user.updatePassword(passwordEncoder.encode(password));

    userRepository.save(user);

    cacheService.remove(email);

  }

}
