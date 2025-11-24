package com.arch.micro_service.auth_server.user.application.service.impl;

import com.arch.micro_service.auth_server.user.application.usecase.UserFindUseCase;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.entity.UserImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserFindUseCase userFindUseCase;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.trace("Authenticating User : {}", username);
    User existingUser = userFindUseCase.findByUserId(username);
    log.trace("User found : {}", username);
    return new UserImpl(existingUser);
  }
}
