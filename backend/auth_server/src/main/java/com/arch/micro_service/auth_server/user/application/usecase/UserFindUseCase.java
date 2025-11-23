package com.arch.micro_service.auth_server.user.application.usecase;

import java.util.UUID;

import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.exception.UserException;
import com.arch.micro_service.auth_server.user.infrastructure.persistence.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFindUseCase {

  private final UserRepository userRepository;

  public User findById(String id) {
    User user = userRepository.findById(UUID.fromString(id)).orElseThrow(() -> UserException.notFound(id));
    if (user.isDeleted()) {
      throw UserException.notFound(id);
    }
    return user;
  }

}
