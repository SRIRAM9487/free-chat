package com.arch.micro_service.chat_server.chatgroup.domain.entity;

import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.ChatGroupResponse;
import com.arch.micro_service.chat_server.shared.domain.BasedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatGroup extends BasedEntity {
  private String description;

  public ChatGroupResponse toResponse() {
    return new ChatGroupResponse(
        this.getId(),
        this.getName(),
        this.getDescription(),
        this.getCreatedAt(),
        this.getCreatedBy(),
        this.getUpdatedAt(),
        this.getCreatedBy());
  }
}
