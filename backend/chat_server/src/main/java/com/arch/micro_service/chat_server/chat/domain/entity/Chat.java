package com.arch.micro_service.chat_server.chat.domain.entity;

import com.arch.micro_service.chat_server.shared.domain.IdEntity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Chat extends IdEntity {

  @Column(name = "message")
  private String message;

}
