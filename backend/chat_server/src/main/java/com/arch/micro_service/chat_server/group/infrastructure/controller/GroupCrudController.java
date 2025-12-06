package com.arch.micro_service.chat_server.group.infrastructure.controller;

import java.util.List;

import com.arch.micro_service.chat_server.group.application.constant.GroupConstant;
import com.arch.micro_service.chat_server.group.application.service.GroupCrudService;
import com.arch.micro_service.chat_server.group.infrastructure.dto.mapper.GroupMapper;
import com.arch.micro_service.chat_server.group.infrastructure.dto.request.GroupCreateRequest;
import com.arch.micro_service.chat_server.group.infrastructure.dto.response.GroupDetailsResponse;
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
@RequiredArgsConstructor
@RequestMapping("/v1/group")
public class GroupCrudController {

  private final GroupCrudService groupCrudService;
  private final GroupMapper groupMapper;

  @GetMapping
  @PreAuthorize("hasAuthority('GROUP_READ')")
  public ResponseEntity<ApiResponse<List<GroupDetailsResponse>>> getAll() {
    var chatters = groupCrudService
        .getAll()
        .stream()
        .map(groupMapper::fromGroup)
        .toList();
    var response = ApiResponse.create(chatters);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('GROUP_READ')")
  public ResponseEntity<ApiResponse<GroupDetailsResponse>> get(@PathVariable("id") String id) {
    var chatter = groupMapper.fromGroup(groupCrudService.get(id));
    var response = ApiResponse.create(chatter);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('GROUP_CREATE')")
  public ResponseEntity<ApiResponse<String>> create(@RequestBody GroupCreateRequest createRequest) {
    groupCrudService.create(createRequest);
    var response = ApiResponse.create(GroupConstant.CREATE);
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/update/{id}")
  @PreAuthorize("hasAuthority('GROUP_UPDATE')")
  public ResponseEntity<ApiResponse<String>> update(@PathVariable("id") String id,
      @RequestBody GroupCreateRequest createRequest) {
    groupCrudService.update(id, createRequest);
    var response = ApiResponse.create(GroupConstant.UPDATE);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('GROUP_DELETE')")
  public ResponseEntity<ApiResponse<String>> update(@PathVariable("id") String id) {
    groupCrudService.delete(id);
    var response = ApiResponse.create(GroupConstant.DELETE);
    return ResponseEntity.ok(response);
  }

}
