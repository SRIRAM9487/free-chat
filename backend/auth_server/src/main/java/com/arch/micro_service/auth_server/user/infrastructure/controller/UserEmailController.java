package com.arch.micro_service.auth_server.user.infrastructure.controller;

import com.arch.micro_service.auth_server.shared.infrastructure.dto.api.ApiResponse;
import com.arch.micro_service.auth_server.user.application.service.UserEmailService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserEmailController {

  public final UserEmailService userEmailService;

  @GetMapping("/email/verify/{id}")
  public ResponseEntity<ApiResponse<String>> emailVerificationRequest(@PathVariable("id") String id) {
    userEmailService.sendVerification(id);
    var response = ApiResponse.create("Email Verification sent");
    return ResponseEntity.ok(response);
  }

  @GetMapping("/email/verify")
  public ResponseEntity<ApiResponse<String>> emailVerification(@RequestParam("email") String email,
      @RequestParam("token") String token) {
    userEmailService.verifyEmail(email, token);
    var response = ApiResponse.create("Email verification successfull");
    return ResponseEntity.ok(response);
  }
}
