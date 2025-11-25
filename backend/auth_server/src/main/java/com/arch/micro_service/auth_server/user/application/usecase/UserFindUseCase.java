package com.arch.micro_service.auth_server.user.application.usecase;

import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.exception.UserException;
import com.arch.micro_service.auth_server.user.domain.vo.Email;
import com.arch.micro_service.auth_server.user.infrastructure.persistence.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFindUseCase {

  private final UserRepository userRepository;

  public User findById(String id) {
    User user = userRepository.findById(Long.parseLong(id)).orElseThrow(() -> UserException.notFound(id));
    if (user.isDeleted()) {
      throw UserException.notFound(id);
    }
    return user;
  }

  public User findByEmail(String email) {
    User user = userRepository.findByEmail_Value(email).orElseThrow(() -> UserException.notFound(email));
    if (user.isDeleted())
      throw UserException.notFound(email);
    return user;
  }

  public User findByUserName(String userName) {
    User user = userRepository.findByUserName(userName).orElseThrow(() -> UserException.notFound(userName));
    if (user.isDeleted())
      throw UserException.notFound(userName);
    return user;
  }

  public User findByUserId(String id) {
    if (Email.isEmail(id))
      return findByEmail(id);

    try {
      Long.parseLong(id);
      return findById(id);
    } catch (NumberFormatException e) {
    }

    return findByUserName(id);
  }

}
