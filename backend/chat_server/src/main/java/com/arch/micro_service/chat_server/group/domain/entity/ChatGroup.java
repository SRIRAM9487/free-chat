package com.arch.micro_service.chat_server.group.domain.entity;

import com.arch.micro_service.chat_server.shared.domain.BasedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatGroup extends BasedEntity {
  private String description;
}
