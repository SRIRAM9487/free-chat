package com.arch.micro_service.auth_server.role.infrastructure.controller.role;

import java.util.List;

import com.arch.micro_service.auth_server.role.application.service.role.RoleService;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RoleUserMetaDataResponse;
import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/role")
@RequiredArgsConstructor
public class RoleController {

  private final RoleService service;

  @PatchMapping("/toggle/{id}")
  public ResponseEntity<ApiResponse<String>> toggleStatus(@PathVariable("id") String id) {
    var response = ApiResponse.create(service.toggleActive(id));
    return ResponseEntity.ok(response);
  }

  @GetMapping("/user/meta")
  public ResponseEntity<ApiResponse<List<RoleUserMetaDataResponse>>> userMeta() {
    var response = ApiResponse.create(service.fetchRole());
    return ResponseEntity.ok(response);
  }

}
