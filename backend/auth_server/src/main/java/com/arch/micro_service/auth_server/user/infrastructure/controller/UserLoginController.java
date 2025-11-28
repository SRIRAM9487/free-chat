package com.arch.micro_service.auth_server.user.infrastructure.controller;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiResponse;
import com.arch.micro_service.auth_server.user.application.service.UserLoginService;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserLoginRequest;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserLoginResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/v1/user")
@RestController
public class UserLoginController {

  private final UserLoginService userLoginService;

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<UserLoginResponse>> login(@RequestBody UserLoginRequest requestDto) {
    var response = ApiResponse.create(userLoginService.login(requestDto));
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/login/verify")
  @PreAuthorize("hasAuthority('USER_LOGIN')")
  public ResponseEntity<ApiResponse<String>> verifyUser(
      @RequestParam("userId") String userId) {
    userLoginService.userVerify(userId);
    var response = ApiResponse.create("User verified successfully");
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/login/reset")
  @PreAuthorize("hasAuthority('USER_PASSWORD_RESET')")
  public ResponseEntity<ApiResponse<String>> passwordReset(
      @RequestParam("userId") String userId) {
    userLoginService.resetPassword(userId);
    var response = ApiResponse.create("User password reseted");
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/login/new")
  public ResponseEntity<ApiResponse<String>> newPassword(@RequestParam("toekn") String token,
      @RequestBody UserLoginRequest userLoginRequestDto) {
    userLoginService.newPassword(token, userLoginRequestDto);
    var response = ApiResponse.create("Password updated");
    return ResponseEntity.ok(response);
  }

}
