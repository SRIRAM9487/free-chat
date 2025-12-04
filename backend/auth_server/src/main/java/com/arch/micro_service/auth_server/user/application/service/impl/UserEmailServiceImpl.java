package com.arch.micro_service.auth_server.user.application.service.impl;

import com.arch.micro_service.auth_server.message.infrastructure.event.EmailVerificationEvent;
import com.arch.micro_service.auth_server.message.infrastructure.publisher.EmailEventPublisher;
import com.arch.micro_service.auth_server.user.application.service.CacheService;
import com.arch.micro_service.auth_server.user.application.service.TokenGeneratorService;
import com.arch.micro_service.auth_server.user.application.service.UserEmailService;
import com.arch.micro_service.auth_server.user.application.usecase.UserFindUseCase;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.exception.EmailException;
import com.arch.micro_service.auth_server.user.infrastructure.persistence.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserEmailServiceImpl implements UserEmailService {

  private final TokenGeneratorService tokenGeneratorService;
  private final EmailEventPublisher emailEventPublisher;
  private final UserFindUseCase userFindUseCase;
  private final CacheService cacheService;
  private final UserRepository userRepository;
  private final Logger log = LoggerFactory.getLogger("MethodLogger");

  @Override
  public void sendVerification(String userId) {
    log.trace("Email verification send requested for {}", userId);
    String token = tokenGeneratorService.generateToken();
    User user = userFindUseCase.findByUserId(userId);
    emailEventPublisher.publishVerificationEmail(new EmailVerificationEvent(user.getEmail().value(), userId, token));
    cacheService.save(user.getEmail().value(), token);
  }

  @Override
  public void verifyEmail(String email, String token) {

    log.trace("Verifing the user email {}", email);

    User user = userFindUseCase.findByEmail(email);

    if (!cacheService.retrive(email).equals(token)) {
      EmailException exception = EmailException.tokenVerificationFailed();
      log.trace("Token Invalid");
      throw exception;
    }

    user.verifyEmail();
    log.debug("Email verified.");
    log.trace("User {} Email verified", user.getUserName());
    userRepository.save(user);

    cacheService.remove(email);
  }

}
