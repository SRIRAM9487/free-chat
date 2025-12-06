package com.arch.micro_service.chat_server.chat.domain.entity;

import com.arch.micro_service.chat_server.chatter.domain.entity.Chatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "simple_chat")
public class SimpleChat extends Chat {

  @ManyToOne
  @JoinColumn(name = "chatters_id", referencedColumnName = "id")
  private Chatter sender;

  @ManyToOne
  @JoinColumn(name = "chatters_id", referencedColumnName = "id")
  private Chatter receiver;

  @Column(name = "read")
  private boolean read;
}
