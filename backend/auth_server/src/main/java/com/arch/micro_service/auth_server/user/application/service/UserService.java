package com.arch.micro_service.auth_server.user.application.service;

import com.arch.micro_service.auth_server.shared.application.service.AbstractCrudService;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.exception.UserException;
import com.arch.micro_service.auth_server.user.domain.vo.Email;
import com.arch.micro_service.auth_server.user.infrastructure.persistence.UserRepository;

import org.springframework.stereotype.Service;

/**
 *
 * User Service
 *
 */
@Service
public class UserService extends AbstractCrudService<User, UserException> {

  private final UserRepository userRepository;
  private final String UUIDPATTERN = "^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$";

  public UserService(UserRepository repository) {
    super(repository);
    this.userRepository = repository;
  }

  public User findByEmail(String email) {
    User user = userRepository.findByEmail_Value(email).orElseThrow(UserException::notFound);
    if (user.isDeleted())
      throw UserException.notFound();
    return user;
  }

  public User findByEmail(Email email) {
    return findByEmail(email.value());
  }

  public User findByUserName(String userName) {
    User user = userRepository.findByUserName(userName).orElseThrow(UserException::notFound);
    if (user.isDeleted())
      throw UserException.notFound();
    return user;
  }

  public User findByUserId(String id) {
    if (Email.isEmail(id))
      return findByEmail(id);

    if (id.matches(UUIDPATTERN))
      return findById(id);

    return findByUserName(id);
  }

  public User deleteByUserId(String id) {
    User user = findByUserId(id);
    user.softDelete();
    return save(user);
  }

  @Override
  public UserException notFound() {
    return UserException.notFound();
  }

}
