package com.arch.micro_service.auth_server.user.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import com.arch.micro_service.auth_server.user.domain.exception.UserException;
import com.arch.micro_service.auth_server.user.domain.exception.type.UserExceptionType;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserLoginRequest;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserLoginResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class UserAuthServiceTest {

  @Autowired
  private UserLoginService userAuthService;

  @Test
  @Transactional
  void login() {
    UserLoginRequest req = new UserLoginRequest("admin", "tester1234");
    UserLoginResponse res = userAuthService.login(req);
    assertEquals("2", res.userId());
    assertEquals("admin", res.userName());
    assertNotNull(res.token());
  }

  @Test
  @Transactional
  void loginEmailNotVerified() {
    UserLoginRequest req = new UserLoginRequest("manager", "tester1234");
    UserException exception = assertThrowsExactly(UserException.class, () -> userAuthService.login(req));

    assertEquals(UserExceptionType.EMAIL_NOT_VERIFIED.name(), exception.getCode());
  }

  @Test
  @Transactional
  void loginBadCredentials() {
    UserLoginRequest req = new UserLoginRequest("admin", "tester234");
    assertThrowsExactly(BadCredentialsException.class, () -> userAuthService.login(req));
  }

}
