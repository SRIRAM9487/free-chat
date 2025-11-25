package com.arch.micro_service.auth_server.user.infrastructure.controller;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiResponse;
import com.arch.micro_service.auth_server.user.application.service.UserAuthService;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserLoginRequest;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserLoginResponse;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserPasswordVerificationResponse;

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
public class UserAuthController {

  private final UserAuthService userAuthService;

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<UserLoginResponse>> login(@RequestBody UserLoginRequest requestDto) {
    var response = ApiResponse.create(userAuthService.login(requestDto));
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/login/verify")
  @PreAuthorize("hasAuthority('USER_LOGIN')")
  public ResponseEntity<ApiResponse<UserPasswordVerificationResponse>> verifyUser(
      @RequestParam("userId") String userId) {
    var response = ApiResponse.create(userAuthService.userVerify(userId));
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/login/reset")
  @PreAuthorize("hasAuthority('USER_PASSWORD_RESET')")
  public ResponseEntity<ApiResponse<UserPasswordVerificationResponse>> passwordReset(
      @RequestParam("userId") String userId) {
    var response = ApiResponse.create(userAuthService.resetPassword(userId));
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/login/new")
  public ResponseEntity<ApiResponse<UserPasswordVerificationResponse>> newPassword(@RequestParam("toekn") String token,
      @RequestBody UserLoginRequest userLoginRequestDto) {
    var response = ApiResponse.create(userAuthService.newPassword(token, userLoginRequestDto));
    return ResponseEntity.ok(response);
  }

}
