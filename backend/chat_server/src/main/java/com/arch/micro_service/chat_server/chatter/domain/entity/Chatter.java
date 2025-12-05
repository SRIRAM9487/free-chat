package com.arch.micro_service.chat_server.chatter.domain.entity;

import java.util.ArrayList;
import java.util.List;

import com.arch.micro_service.chat_server.chat.domain.entity.SimpleChat;
import com.arch.micro_service.chat_server.group.domain.entity.GroupMember;
import com.arch.micro_service.chat_server.shared.domain.BasedEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "chatters")
public class Chatter extends BasedEntity {

  @Column(name = "user_id", unique = true)
  private Long userId;

  private boolean banned;

  @OneToMany(mappedBy = "chatter")
  @JsonManagedReference
  @Builder.Default
  private List<GroupMember> groupMembers = new ArrayList<>();

  @OneToMany(mappedBy = "chatter")
  @JsonManagedReference
  @Builder.Default
  private List<SimpleChat> simpleChats = new ArrayList<>();

}
