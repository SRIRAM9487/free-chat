package com.arch.micro_service.auth_server.user.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.arch.micro_service.auth_server.shared.domain.constant.Gender;
import com.arch.micro_service.auth_server.user.application.service.UserCrudService;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.exception.UserException;
import com.arch.micro_service.auth_server.user.domain.exception.type.UserExceptionType;
import com.arch.micro_service.auth_server.user.domain.vo.Email;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class UserFindUseCaseTest {

  @Autowired
  private UserFindUseCase userFindUseCase;

  @Autowired
  private UserCrudService userCrudService;

  @Test
  @Transactional
  void findById() {
    User user = userFindUseCase.findById("1");
    assertEquals(1L, user.getId());
    assertEquals("Admin User", user.getName());
    assertEquals("admin", user.getUserName());
    assertNotNull(user.getPassword());
    assertEquals(Email.create("admin@example.com", true), user.getEmail());
    assertEquals(Gender.MALE, user.getGender());
    assertTrue(user.isAccountNonLocked());
    assertTrue(user.isAccountNonExpired());
    assertTrue(user.isEnabled());
  }

  @Test
  @Transactional
  void findByIdNotFound() {

    UserException userException = assertThrowsExactly(UserException.class, () -> userFindUseCase.findById("999999"));
    assertEquals(UserExceptionType.USER_NOT_FOUND.name(), userException.getCode());

    userCrudService.delete("1");

    userException = assertThrowsExactly(UserException.class, () -> userFindUseCase.findById("1"));
    assertEquals(UserExceptionType.USER_NOT_FOUND.name(), userException.getCode());
  }

  @Test
  @Transactional
  void findByEmail() {
    User user = userFindUseCase.findByEmail("admin@example.com");
    assertEquals(1L, user.getId());
    assertEquals("Admin User", user.getName());
    assertEquals("admin", user.getUserName());
  }

  @Test
  @Transactional
  void findByEmailNotFound() {

    UserException userException = assertThrowsExactly(UserException.class,
        () -> userFindUseCase.findByEmail("dummy@example.com"));
    assertEquals(UserExceptionType.USER_NOT_FOUND.name(), userException.getCode());

    userCrudService.delete("1");

    userException = assertThrowsExactly(UserException.class, () -> userFindUseCase.findByEmail("admin@example.com"));
    assertEquals(UserExceptionType.USER_NOT_FOUND.name(), userException.getCode());
  }

  @Test
  @Transactional
  void findByUserName() {
    User user = userFindUseCase.findByUserName("admin");
    assertEquals(1L, user.getId());
    assertEquals("Admin User", user.getName());
    assertEquals("admin@example.com", user.getEmail().value());
  }

  @Test
  @Transactional
  void findByUserNameNotFound() {
    UserException userException = assertThrowsExactly(UserException.class,
        () -> userFindUseCase.findByUserName("tester"));
    assertEquals(UserExceptionType.USER_NOT_FOUND.name(), userException.getCode());

    userCrudService.delete("1");

    userException = assertThrowsExactly(UserException.class, () -> userFindUseCase.findByUserName("admin"));
    assertEquals(UserExceptionType.USER_NOT_FOUND.name(), userException.getCode());
  }

  @Test
  @Transactional
  void findByUserId() {
    User user = userFindUseCase.findByUserId("admin@example.com");
    assertEquals(1L, user.getId());
    user = userFindUseCase.findByUserId("1");
    assertEquals(1L, user.getId());
    user = userFindUseCase.findByUserId("admin");
    assertEquals(1L, user.getId());
  }
}
