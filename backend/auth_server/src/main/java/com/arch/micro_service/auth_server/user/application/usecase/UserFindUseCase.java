package com.arch.micro_service.auth_server.user.application.usecase;

import java.util.UUID;

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
  private final String UUIDPATTERN = "^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$";

  public User findById(String id) {
    User user = userRepository.findById(UUID.fromString(id)).orElseThrow(() -> UserException.notFound(id));
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

  public User findByEmail(Email email) {
    return findByEmail(email.value());
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

    if (id.matches(UUIDPATTERN))
      return findById(id);

    return findByUserName(id);
  }

}
