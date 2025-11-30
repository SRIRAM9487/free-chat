package com.arch.micro_service.gateway_server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("fallback")
public class FallBackController {

  @RequestMapping("/arch-byte")
  public Mono<ResponseEntity<String>> handleAuthServiceFallBack() {
    return Mono.just(ResponseEntity
        .status(HttpStatus.SERVICE_UNAVAILABLE)
        .body("Auth service is unavailable right now"));
  }
}
