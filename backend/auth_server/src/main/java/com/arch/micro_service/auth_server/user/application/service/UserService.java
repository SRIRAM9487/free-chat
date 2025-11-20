package com.arch.micro_service.auth_server.user.application.service;

import com.arch.micro_service.auth_server.shared.application.service.AbstractCrudService;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.exception.UserException;
import com.arch.micro_service.auth_server.user.infrastructure.persistence.UserRepository;

import org.springframework.stereotype.Service;

/**
 *
 * User Service
 *
 */
@Service
public class UserService extends AbstractCrudService<User, UserException> {

  public UserService(UserRepository repository) {
    super(repository);
  }

  public User findByUserId(String id) {
    return null;
  }

  @Override
  public UserException notFound() {
    return UserException.notFound();
  }

}
