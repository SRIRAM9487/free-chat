package com.arch.micro_service.chat_server.chat.domain.entity;

import com.arch.micro_service.chat_server.group.domain.entity.GroupMember;

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
@Table(name = "group_chat")
public class GroupChat extends Chat {

  @ManyToOne
  @JoinColumn(name = "groupChat_id", referencedColumnName = "id")
  private GroupMember groupMembers;

}
