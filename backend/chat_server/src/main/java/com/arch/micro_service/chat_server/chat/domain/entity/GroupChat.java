package com.arch.micro_service.chat_server.chat.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupChat extends Chat {
  private Long groupMemberId;
}
