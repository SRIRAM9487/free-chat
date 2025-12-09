package com.arch.micro_service.chat_server.chatter.infrastructure.controller;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.application.constant.ChatGroupConstant;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.request.ChatGroupCreateRequest;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.ChatGroupResponse;
import com.arch.micro_service.chat_server.chatter.application.constant.ChatterConstant;
import com.arch.micro_service.chat_server.chatter.application.service.ChatterCrudService;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.request.ChatterCreateRequest;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.response.ChatterResponse;
import com.arch.micro_service.chat_server.shared.infrastructure.dto.api.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/v1/chatter")
@RequiredArgsConstructor
public class ChatterCrudController {

  private final ChatterCrudService crudService;

  @GetMapping
  @PreAuthorize("hasAuthority('CHATTER_READ')")
  public ResponseEntity<ApiResponse<List<ChatterResponse>>> getAll() {
    var groups = crudService.findAll().stream().map(c -> c.toResponse()).toList();
    var response = ApiResponse.create(groups);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('CHATTER_READ')")
  public ResponseEntity<ApiResponse<ChatterResponse>> get(@PathVariable("id") Long id) {
    var response = ApiResponse.create(crudService.findById(id).toResponse());
    return ResponseEntity.ok(response);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('CHATTER_CREATE')")
  public ResponseEntity<ApiResponse<String>> create(@RequestBody ChatterCreateRequest request) {
    crudService.create(request);
    var response = ApiResponse.create(ChatterConstant.CREATE);
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/update/{id}")
  @PreAuthorize("hasAuthority('CHATTER_UPDATE')")
  public ResponseEntity<ApiResponse<String>> update(@PathVariable("id") Long id,
      @RequestBody ChatterCreateRequest request) {
    crudService.update(id, request);
    var response = ApiResponse.create(ChatterConstant.UPDATE);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('CHATTER_DELETE')")
  public ResponseEntity<ApiResponse<String>> delete(@PathVariable("id") Long id) {
    crudService.delete(id);
    var response = ApiResponse.create(ChatterConstant.DELETE);
    return ResponseEntity.ok(response);
  }

}
