package com.arch.micro_service.chat_server.chatter.infrastructure.controller;

import java.util.List;

import com.arch.micro_service.chat_server.chatter.application.constant.ChatterConstant;
import com.arch.micro_service.chat_server.chatter.application.service.ChatterCrudService;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.mapper.ChatterMapper;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.request.ChatterCreateRequest;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.response.ChatterDetailsResponse;
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

  private final ChatterCrudService chatterCrudService;
  private final ChatterMapper chatterMapper;

  @GetMapping
  @PreAuthorize("hasAuthority('CHATTER_READ')")
  public ResponseEntity<ApiResponse<List<ChatterDetailsResponse>>> getAll() {
    var chatters = chatterCrudService
        .getAll()
        .stream()
        .map(chatterMapper::fromChatter)
        .toList();
    var response = ApiResponse.create(chatters);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('CHATTER_READ')")
  public ResponseEntity<ApiResponse<ChatterDetailsResponse>> get(@PathVariable("id") String id) {
    var chatter = chatterMapper.fromChatter(chatterCrudService.get(id));
    var response = ApiResponse.create(chatter);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('CHATTER_CREATE')")
  public ResponseEntity<ApiResponse<String>> create(@RequestBody ChatterCreateRequest createRequest) {
    chatterCrudService.create(createRequest);
    var response = ApiResponse.create(ChatterConstant.CREATE);
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/update/{id}")
  @PreAuthorize("hasAuthority('CHATTER_UPDATE')")
  public ResponseEntity<ApiResponse<String>> update(@PathVariable("id") String id,
      @RequestBody ChatterCreateRequest createRequest) {
    chatterCrudService.update(id, createRequest);
    var response = ApiResponse.create(ChatterConstant.UPDATE);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('CHATTER_DELETE')")
  public ResponseEntity<ApiResponse<String>> update(@PathVariable("id") String id) {
    chatterCrudService.delete(id);
    var response = ApiResponse.create(ChatterConstant.DELETE);
    return ResponseEntity.ok(response);
  }
}
