package com.arch.micro_service.auth_server.role.infrastructure.controller.role;

import com.arch.micro_service.auth_server.role.application.usecase.role.RoleUpdateUseCase;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/role")
@RequiredArgsConstructor
public class RoleController {

  private final RoleUpdateUseCase updateUseCase;

  @PatchMapping("/toggle/{id}")
  public ResponseEntity<ApiResponse<String>> toggleStatus(@PathVariable("id") String id) {
    var response = ApiResponse.create(updateUseCase.toggleActive(id));
    return ResponseEntity.ok(response);
  }
}
