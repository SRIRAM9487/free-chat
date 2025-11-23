package com.arch.micro_service.auth_server.user.infrastructure.controller;

import com.arch.micro_service.auth_server.user.application.service.UserAuthService;

import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<ApiResponseDto<UserLoginResponseDto>> login(@RequestBody UserLoginRequestDto requestDto) {
    var response = ApiResponseDto.create(loginUseCase.login(requestDto));
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/login/verify")
  public ResponseEntity<ApiResponseDto<UserPasswordVeificationResponseDto>> verifyUser(
      @RequestParam("userId") String userId) {
    var response = ApiResponseDto.create(passwordUseCase.userVerify(userId));
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/login/reset")
  public ResponseEntity<ApiResponseDto<UserPasswordVeificationResponseDto>> passwordReset(
      @RequestParam("userId") String userId) {
    var response = ApiResponseDto.create(passwordUseCase.resetPassword(userId));
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/login/new")
  public ResponseEntity<ApiResponseDto<UserPasswordVeificationResponseDto>> newPassword(
      @RequestParam("toekn") String token,
      @RequestBody UserLoginRequestDto userLoginRequestDto) {
    var response = ApiResponseDto.create(passwordUseCase.newPassword(token, userLoginRequestDto));
    return ResponseEntity.ok(response);
  }

}
