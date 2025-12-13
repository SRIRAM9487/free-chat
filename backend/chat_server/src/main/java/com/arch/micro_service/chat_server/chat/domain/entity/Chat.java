package com.arch.micro_service.chat_server.chat.domain.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
  private Long id;
  private String message;
  private Long chatterId;
  private Long groupId;
  private LocalDateTime createdAt;
}
