package com.arch.micro_service.chat_server.chatter.domain.entity;

import com.arch.micro_service.chat_server.chatter.infrastructure.dto.response.ChatterResponse;
import com.arch.micro_service.chat_server.shared.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Chatter extends BaseEntity {

  private Long userId;

  public ChatterResponse toResponse() {
    return new ChatterResponse(
        this.getId(),
        this.getName(),
        this.getUserId(),
        this.getCreatedAt(),
        this.getCreatedBy(),
        this.getUpdatedAt(),
        this.getUpdatedBy());
  }
}
