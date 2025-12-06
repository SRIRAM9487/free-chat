package com.arch.micro_service.auth_server.user.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import com.arch.micro_service.auth_server.log.CustomLogger;
import com.arch.micro_service.auth_server.shared.domain.constant.Gender;
import com.arch.micro_service.auth_server.testcontainer.AbstractTestContainer;
import com.arch.micro_service.auth_server.user.application.service.UserCrudService;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.exception.UserException;
import com.arch.micro_service.auth_server.user.domain.exception.type.UserExceptionType;
import com.arch.micro_service.auth_server.user.domain.vo.Email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

public class UserFindUseCaseTest extends AbstractTestContainer {

  @Autowired
  private UserFindUseCase userFindUseCase;

  @Autowired
  private UserCrudService userCrudService;

  @MockitoBean
  private CustomLogger customLogger;

  @BeforeEach
  void setup() {
    doNothing().when(customLogger).success(anyString(), anyString(), any(), any());
    doNothing().when(customLogger).failure(anyString(), anyString(), any());
  }

  @Test
  @Transactional
  void findById() {
    User user = userFindUseCase.findById("1");
    assertEquals(1L, user.getId());
    assertEquals("Sudo Master", user.getName());
    assertEquals("sudo", user.getUserName());
    assertNotNull(user.getPassword());
    assertEquals(Email.create("sudo@devops.com", true), user.getEmail());
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
    User user = userFindUseCase.findByEmail("sudo@devops.com");
    assertEquals(1L, user.getId());
    assertEquals("Sudo Master", user.getName());
    assertEquals("sudo", user.getUserName());
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
    User user = userFindUseCase.findByUserName("sudo");
    assertEquals(1L, user.getId());
    assertEquals("Sudo Master", user.getName());
    assertEquals("sudo@devops.com", user.getEmail().value());
  }

  @Test
  @Transactional
  void findByUserNameNotFound() {
    UserException userException = assertThrowsExactly(UserException.class,
        () -> userFindUseCase.findByUserName("tester"));
    assertEquals(UserExceptionType.USER_NOT_FOUND.name(), userException.getCode());

    userCrudService.delete("1");

    userException = assertThrowsExactly(UserException.class, () -> userFindUseCase.findByUserName("sudo"));
    assertEquals(UserExceptionType.USER_NOT_FOUND.name(), userException.getCode());
  }

  @Test
  @Transactional
  void findByUserId() {
    User user = userFindUseCase.findByUserId("sudo@devops.com");
    assertEquals(1L, user.getId());
    user = userFindUseCase.findByUserId("1");
    assertEquals(1L, user.getId());
    user = userFindUseCase.findByUserId("sudo");
    assertEquals(1L, user.getId());
  }
}
