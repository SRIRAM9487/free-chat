package com.arch.micro_service.auth_server.user.application.usecase;

import com.arch.micro_service.auth_server.log.CustomLogger;
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
  private final CustomLogger customLogger;

  public User findById(String id) {
    User user = userRepository.findById(Long.parseLong(id)).orElseThrow(() -> {
      var ex = UserException.notFound();
      customLogger.failure("User not found " + id, ex);
      return ex;
    });
    if (user.isDeleted()) {
      var ex = UserException.notFound();
      customLogger.failure("User not found " + id, ex);
      throw ex;
    }
    return user;
  }

  public User findByEmail(String email) {

    User user = userRepository.findByEmail_Value(email).orElseThrow(() -> {
      var ex = UserException.notFound();
      customLogger.failure("User not found " + email, ex);
      return ex;
    });

    if (user.isDeleted()) {
      var ex = UserException.notFound();
      customLogger.failure("User not found " + email, ex);
      throw ex;
    }

    return user;
  }

  public User findByUserName(String userName) {

    User user = userRepository.findByUserName(userName).orElseThrow(() -> {
      var ex = UserException.notFound();
      customLogger.failure("User not found " + userName, ex);
      return ex;
    });
    if (user.isDeleted()) {
      var ex = UserException.notFound();
      customLogger.failure("User not found " + userName, ex);
      throw ex;
    }
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
