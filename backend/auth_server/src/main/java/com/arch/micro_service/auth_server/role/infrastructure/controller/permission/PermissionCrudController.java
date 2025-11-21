package com.arch.micro_service.auth_server.role.infrastructure.controller.permission;

import java.util.List;

import com.arch.micro_service.auth_server.role.application.usecase.permission.PermissionCreateUseCase;
import com.arch.micro_service.auth_server.role.application.usecase.permission.PermissionDeleteUseCase;
import com.arch.micro_service.auth_server.role.application.usecase.permission.PermissionReadUseCase;
import com.arch.micro_service.auth_server.role.application.usecase.permission.PermissionUpdateUseCase;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.request.PermissionCreateRequest;
import com.arch.micro_service.auth_server.role.infrastructure.dto.permission.response.PermissionDetailResponse;
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
@RequestMapping("/v1/permission")
@RequiredArgsConstructor
public class PermissionCrudController
    implements AbstractCrudController<PermissionCreateRequest, PermissionDetailResponse> {

  private final PermissionCreateUseCase createUseCase;
  private final PermissionReadUseCase readUseCase;
  private final PermissionUpdateUseCase updateUseCase;
  private final PermissionDeleteUseCase deleteUseCase;

  @Override
  @GetMapping
  public ResponseEntity<ApiResponse<List<PermissionDetailResponse>>> getAll() {
    var response = ApiResponse.create(readUseCase.getAll());
    return ResponseEntity.ok(response);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<PermissionDetailResponse>> get(@PathVariable("id") String id) {
    var response = ApiResponse.create(readUseCase.getById(id));
    return ResponseEntity.ok(response);
  }

  @Override
  @PostMapping("/create")
  public ResponseEntity<ApiResponse<String>> create(@RequestBody PermissionCreateRequest request) {
    var response = ApiResponse.create(createUseCase.create(request));
    return ResponseEntity.ok(response);
  }

  @Override
  @PatchMapping("/update/{id}")
  public ResponseEntity<ApiResponse<String>> update(@PathVariable("id") String id,
      @RequestBody PermissionCreateRequest request) {
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
