package com.arch.micro_service.chat_server.chatgroup.infrastructure.controller;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.application.constant.ChatGroupConstant;
import com.arch.micro_service.chat_server.chatgroup.application.constant.GroupMemberConstant;
import com.arch.micro_service.chat_server.chatgroup.application.service.GroupMemberService;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.request.GroupMemberCreateRequest;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.GroupMemberResponse;
import com.arch.micro_service.chat_server.shared.infrastructure.dto.api.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/group-member")
@RequiredArgsConstructor
public class GroupMemberController {

  private final GroupMemberService crudService;

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('GROUP_MEMBER_READ')")
  public ResponseEntity<ApiResponse<GroupMemberResponse>> get(@PathVariable("id") Long id) {
    var response = ApiResponse.create(crudService.findById(id).toResponse());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/group/{id}")
  @PreAuthorize("hasAuthority('GROUP_MEMBER_READ')")
  public ResponseEntity<ApiResponse<List<GroupMemberResponse>>> getByGroupId(@PathVariable("id") Long id) {
    var response = ApiResponse.create(crudService.findByGroupId(id).stream().map(c -> c.toResponse()).toList());
    return ResponseEntity.ok(response);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('GROUP_MEMBER_CREATE')")
  public ResponseEntity<ApiResponse<String>> create(@RequestBody GroupMemberCreateRequest request) {
    crudService.create(request);
    var response = ApiResponse.create(GroupMemberConstant.CREATE);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/restrict/{id}")
  @PreAuthorize("hasAuthority('GROUP_MEMBER_EDIT')")
  public ResponseEntity<ApiResponse<String>> restrict(@RequestParam("restrict") Boolean restrict,
      @PathVariable("id") Long id) {
    crudService.restrict(restrict, id);
    var response = ApiResponse.create(restrict ? GroupMemberConstant.RESTRICTED : GroupMemberConstant.UNRESTRICTED);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/access/{id}")
  @PreAuthorize("hasAuthority('GROUP_MEMBER_EDIT')")
  public ResponseEntity<ApiResponse<String>> access(@RequestParam("access") String access,
      @PathVariable("id") Long id) {
    crudService.access(access, id);
    var response = ApiResponse.create(GroupMemberConstant.ACCESS_UPDATED + "\t" + access);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('GROUP_MEMBER_DELETE')")
  public ResponseEntity<ApiResponse<String>> delete(@PathVariable("id") Long id) {
    crudService.delete(id);
    var response = ApiResponse.create(GroupMemberConstant.DELETE);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/group/{id}")
  @PreAuthorize("hasAuthority('GROUP_MEMBER_DELETE')")
  public ResponseEntity<ApiResponse<String>> deleteByGroupId(@PathVariable("id") Long id) {
    crudService.deleteByGroupId(id);
    var response = ApiResponse.create(GroupMemberConstant.DELETE);
    return ResponseEntity.ok(response);
  }

}
