package com.arch.micro_service.auth_server.role.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.arch.micro_service.auth_server.role.application.service.role.RoleCrudService;
import com.arch.micro_service.auth_server.role.application.usecase.role.RoleFindUseCase;
import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.domain.exception.RoleException;
import com.arch.micro_service.auth_server.role.domain.exception.type.RoleExceptionType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleFindUseCaseTest {

  @Autowired
  private RoleFindUseCase roleFindUseCase;

  @Autowired
  private RoleCrudService roleCrudService;

  @Test
  void getAll() {
    List<Role> roles = roleFindUseCase.findAll();
    assertEquals(5, roles.size());
  }

  @Test
  void findById() {
    Role role = roleFindUseCase.findById("1");
    assertEquals("ADMIN", role.getTitle());
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
}
