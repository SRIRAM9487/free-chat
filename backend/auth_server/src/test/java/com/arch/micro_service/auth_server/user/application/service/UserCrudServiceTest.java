package com.arch.micro_service.auth_server.user.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import java.util.List;

import com.arch.micro_service.auth_server.log.CustomLogger;
import com.arch.micro_service.auth_server.shared.domain.constant.Gender;
import com.arch.micro_service.auth_server.testcontainer.AbstractTestContainer;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.vo.Email;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserCreateRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

public class UserCrudServiceTest extends AbstractTestContainer {

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
  void getAll() {
    List<User> users = userCrudService.getAll();
    assertEquals(13, users.size());
    userCrudService.delete("1");
    users = userCrudService.getAll();
    assertEquals(12, users.size());
  }

  @Test
  @Transactional
  void getById() {
    User user = userCrudService.get("1");
    assertEquals(1L, user.getId());
    assertEquals("Sudo Master", user.getName());
    assertEquals("sudo", user.getUserName());
  }

  @Test
  @Transactional
  void createUser() {

    UserCreateRequest req = new UserCreateRequest("test", "Tester", "test", "test@gmail.com", "MALE", true, false,
        false, List.of(1L, 2L, 3L));

    User user = userCrudService.create(req);

    assertEquals(14L, user.getId());
    assertEquals(req.name(), user.getName());
    assertEquals(req.userName(), user.getUserName());
    assertNotNull(user.getPassword());
    assertEquals(Email.create("test@gmail.com"), user.getEmail());
    assertEquals(Gender.MALE, user.getGender());
    assertEquals(req.accountNonLocked(), user.isAccountNonLocked());
    assertEquals(req.accountNonExpired(), user.isAccountNonExpired());
    assertEquals(req.enabled(), user.isEnabled());
    assertNotNull(user.getCreatedAt());
  }

  @Test
  @Transactional
  void updateUser() {

    UserCreateRequest req = new UserCreateRequest("test", "Tester", "test", "test@gmail.com", "MALE", true, false,
        false, List.of(1L, 2L, 3L));
    User user = userCrudService.update("1", req);
    assertEquals(1L, user.getId());
    assertEquals(req.name(), user.getName());
    assertEquals(req.userName(), user.getUserName());
    assertNotNull(user.getPassword());
    assertEquals(Email.create("test@gmail.com"), user.getEmail());
    assertEquals(Gender.MALE, user.getGender());
    assertEquals(req.accountNonLocked(), user.isAccountNonLocked());
    assertEquals(req.accountNonExpired(), user.isAccountNonExpired());
    assertEquals(req.enabled(), user.isEnabled());
  }

  @Test
  @Transactional
  void deleteUser() {
    User user = userCrudService.delete("1");
    assertNotNull(user.getDeletedAt());

  }

}
