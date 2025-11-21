package com.arch.micro_service.auth_server.shared.application.init;

import java.util.List;

import com.arch.micro_service.auth_server.role.application.service.PermissionService;
import com.arch.micro_service.auth_server.role.application.service.RoleService;
import com.arch.micro_service.auth_server.role.application.usecase.permission.PermissionCreateUseCase;
import com.arch.micro_service.auth_server.role.application.usecase.role.RoleCreateUseCase;
import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request.PermissionCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RoleCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RolePermissionCreateRequest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

  private final PermissionService permissionService;
  private final PermissionCreateUseCase permissionCreateUseCase;

  private final RoleService roleService;
  private final RoleCreateUseCase roleCreateUseCase;

  @Override
  public void run(String... args) throws Exception {

    if (permissionService.count() == 0)
      createPermission();

    if (roleService.count() == 0)
      createRole();
  }

  private void createPermission() {

    List<PermissionCreateRequest> permissionCreateRequests = List.of(
        PermissionCreateRequest.testPermission("Permission"),
        PermissionCreateRequest.testPermission("Role"),
        PermissionCreateRequest.testPermission("User"));

    for (var permissionCreateRequest : permissionCreateRequests) {
      permissionCreateUseCase.create(permissionCreateRequest);
    }
    System.out.println("Permission created");

  }

  public void createRole() {

    List<Permission> permissions = permissionService.findAll();

    List<RolePermissionCreateRequest> admin = permissions
        .stream()
        .map(perm -> new RolePermissionCreateRequest(perm.getId().toString(), true))
        .toList();

    List<RolePermissionCreateRequest> manager = permissions
        .stream()
        .map(perm -> new RolePermissionCreateRequest(perm.getId().toString(), true))
        .toList();

    List<RolePermissionCreateRequest> customer = permissions
        .stream()
        .map(perm -> new RolePermissionCreateRequest(perm.getId().toString(), false))
        .toList();

    List<RoleCreateRequest> roleCreateRequests = List.of(
        new RoleCreateRequest("Admin", true, admin),
        new RoleCreateRequest("Manager", true, manager),
        new RoleCreateRequest("Customer", true, customer));

    for (var roleCreateRequest : roleCreateRequests) {
      roleCreateUseCase.create(roleCreateRequest);
    }
    System.out.println("Role created");

  }
}
