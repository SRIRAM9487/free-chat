package com.arch.micro_service.auth_server.role.infrastructure.controller.role;

import java.util.List;

import com.arch.micro_service.auth_server.role.application.usecase.role.RoleCreateUseCase;
import com.arch.micro_service.auth_server.role.application.usecase.role.RoleDeleteUseCase;
import com.arch.micro_service.auth_server.role.application.usecase.role.RoleReadUseCase;
import com.arch.micro_service.auth_server.role.application.usecase.role.RoleUpdateUseCase;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.request.RoleCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RoleDetailResponse;
import com.arch.micro_service.auth_server.shared.infrastructure.controller.AbstractCrudController;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/role")
@RequiredArgsConstructor
public class RoleCrudController
    implements AbstractCrudController<RoleCreateRequest, RoleDetailResponse> {

  private final RoleCreateUseCase createUseCase;
  private final RoleReadUseCase readUseCase;
  private final RoleUpdateUseCase updateUseCase;
  private final RoleDeleteUseCase deleteUseCase;

  @Override
  @GetMapping
  public ResponseEntity<ApiResponse<List<RoleDetailResponse>>> getAll() {
    var response = ApiResponse.create(readUseCase.getAll());
    return ResponseEntity.ok(response);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<RoleDetailResponse>> get(@PathVariable("id") String id) {
    var response = ApiResponse.create(readUseCase.getById(id));
    return ResponseEntity.ok(response);
  }

  @Override
  @PostMapping("/create")
  public ResponseEntity<ApiResponse<String>> create(@RequestBody RoleCreateRequest request) {
    var response = ApiResponse.create(createUseCase.create(request));
    return ResponseEntity.ok(response);
  }

  @Override
  @PatchMapping("/update/{id}")
  public ResponseEntity<ApiResponse<String>> update(
      @PathVariable("id") String id,
      @RequestBody RoleCreateRequest request) {

    var response = ApiResponse.create(updateUseCase.update(id, request));
    return ResponseEntity.ok(response);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<String>> delete(@PathVariable("id") String id) {
    var response = ApiResponse.create(deleteUseCase.delete(id));
    return ResponseEntity.ok(response);
  }
}
