package com.arch.micro_service.chat_server.chat.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleChat extends Chat {
  private Long senderId;
  private Long recieverId;
  private boolean read;
}
