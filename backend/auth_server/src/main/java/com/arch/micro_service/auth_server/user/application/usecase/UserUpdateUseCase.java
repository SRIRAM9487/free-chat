package com.arch.micro_service.auth_server.user.application.usecase;

import com.arch.micro_service.auth_server.user.application.constant.UserCrudConstant;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserUpdateUseCase {

  public String update() {
    return UserCrudConstant.UPDATE;
  }

}
