package com.arch.micro_service.auth_server.role.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import java.util.List;

import com.arch.micro_service.auth_server.log.CustomLogger;
import com.arch.micro_service.auth_server.role.application.service.role.RoleCrudService;
import com.arch.micro_service.auth_server.role.application.usecase.role.RoleFindUseCase;
import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.domain.exception.RoleException;
import com.arch.micro_service.auth_server.role.domain.exception.type.RoleExceptionType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class RoleFindUseCaseTest {

  @Autowired
  private RoleFindUseCase roleFindUseCase;

  @Autowired
  private RoleCrudService roleCrudService;

  @MockitoBean
  private CustomLogger customLogger;

  @BeforeEach
  void setup() {
    doNothing().when(customLogger).success(anyString(), anyString(), any(), any());
    doNothing().when(customLogger).failure(anyString(), anyString(), any());
  }

  @Test
  void getAll() {
    List<Role> roles = roleFindUseCase.findAll();
    assertEquals(6, roles.size());
  }

  @Test
  void getAllSoftDelete() {
    roleCrudService.delete("1");
    List<Role> roles = roleFindUseCase.findAll();
    assertEquals(5, roles.size());
  }

  @Test
  void findById() {
    Role role = roleFindUseCase.findById("1");
    assertEquals("SUDO", role.getTitle());
    assertTrue(role.isActive());
  }

  @Test
  void findByIdException() {

    RoleException exception = assertThrowsExactly(RoleException.class, () -> roleFindUseCase.findById("9999"));
    assertEquals(RoleExceptionType.ROLE_NOT_FOUND.name(), exception.getCode());

    roleCrudService.delete("1");
    exception = assertThrowsExactly(RoleException.class, () -> roleFindUseCase.findById("1"));
    assertEquals(RoleExceptionType.ROLE_NOT_FOUND.name(), exception.getCode());
  }

  @Test
  void findAllById() {

    List<Long> ids = List.of(1L, 2L, 3L);
    List<Role> roles = roleFindUseCase.findAllById(ids);

    assertNotNull(roles);

    List<Long> roleIds = roles.stream().map(role -> role.getId()).toList();

    assertEquals(ids.size(), roles.size());
    assertTrue(roleIds.containsAll(ids));
  }

  @Test
  void findAllByIdSoftDelete() {

    roleCrudService.delete("1");

    List<Long> ids = List.of(1L, 2L, 3L);
    List<Role> roles = roleFindUseCase.findAllById(ids);

    assertNotNull(roles);

    List<Long> roleIds = roles.stream().map(role -> role.getId()).toList();

    assertEquals(ids.size() - 1, roles.size());
    assertFalse(roleIds.containsAll(ids));
  }
}
