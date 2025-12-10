package com.arch.micro_service.chat_server.chat.domain.entity;

import com.arch.micro_service.chat_server.shared.domain.IdEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Chat extends IdEntity {
  private String message;
}
