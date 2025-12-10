package com.arch.micro_service.chat_server.chatgroup.domain.entity;

import com.arch.micro_service.chat_server.shared.domain.IdEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GroupMember extends IdEntity {
  private Long chatterId;
  private Long groupId;
  private AccessLevel accessLevel;
  private boolean restricted;

}
