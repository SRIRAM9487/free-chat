package com.arch.micro_service.auth_server.shared.application.init;

import java.util.List;
import java.util.UUID;

import com.arch.micro_service.auth_server.role.application.service.permission.PermissionCrudService;
import com.arch.micro_service.auth_server.role.application.service.role.RoleCrudService;
import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request.PermissionCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RoleCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RolePermissionCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.persistence.PermissionRepository;
import com.arch.micro_service.auth_server.role.infrastructure.persistence.RoleRepository;
import com.arch.micro_service.auth_server.user.application.service.UserCrudService;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserCreateRequest;
import com.arch.micro_service.auth_server.user.infrastructure.persistence.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

  private final PermissionRepository permissionRepository;
  private final PermissionCrudService permissionCrudService;

  private final RoleRepository roleRepository;
  private final RoleCrudService roleCrudService;

  private final UserRepository userRepository;
  private final UserCrudService userCrudService;

  @Override
  public void run(String... args) throws Exception {

    if (permissionRepository.count() == 0)
      createPermission();

    if (roleRepository.count() == 0)
      createRole();

    if (userRepository.count() == 0)
      createUser();
  }

  private void createPermission() {

    List<PermissionCreateRequest> permissionCreateRequests = List.of(
        PermissionCreateRequest.testPermission("Permission"),
        PermissionCreateRequest.testPermission("Role"),
        PermissionCreateRequest.testPermission("User"));

    for (var permissionCreateRequest : permissionCreateRequests) {
      permissionCrudService.create(permissionCreateRequest);
    }
    System.out.println("Permission created");

  }

  public void createRole() {

    List<Permission> permissions = permissionRepository.findAll();

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
      roleCrudService.create(roleCreateRequest);
    }

    System.out.println("Role created");

  }

  public void createUser() {
    log.trace("Starting default test user creation...");

    var roles = roleRepository.findAll();
    var adminRole = roles.stream().filter(r -> r.getTitle().equalsIgnoreCase("Admin")).findFirst().orElse(null);
    var managerRole = roles.stream().filter(r -> r.getTitle().equalsIgnoreCase("Manager")).findFirst().orElse(null);
    var customerRole = roles.stream().filter(r -> r.getTitle().equalsIgnoreCase("Customer")).findFirst().orElse(null);

    if (adminRole == null || managerRole == null || customerRole == null) {
      log.error("Roles are not properly initialized, cannot create default users.");
      return;
    }
    for (int i = 1; i <= 10; i++) {
      String name = "Test" + i;
      String username = "test" + i;
      String email = "test" + i + "@example.com";

      List<UUID> roleIds;
      if (i == 1) {
        roleIds = List.of(adminRole.getId());
      } else if (i <= 3) {
        roleIds = List.of(managerRole.getId());
      } else {
        roleIds = List.of(customerRole.getId());
      }

      UserCreateRequest userRequest = new UserCreateRequest(
          name,
          username,
          "test",
          email,
          i % 2 == 0 ? "FEMALE" : "MALE",
          true,
          true,
          true,
          roleIds);

      userCrudService.create(userRequest);
      log.info("Created test user '{}'", username);
    }

    log.info("All default test users created successfully.");
  }
}
