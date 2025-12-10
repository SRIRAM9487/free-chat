package com.arch.micro_service.chat_server.chatgroup.domain.entity;

import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.GroupMemberResponse;
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

  public GroupMemberResponse toResponse() {
    return new GroupMemberResponse(this.getId(), this.chatterId, this.getGroupId(), this.accessLevel.name(),
        this.isRestricted(), this.getCreatedAt(), this.getCreatedBy(), this.getUpdatedAt(), this.getUpdatedBy());
  }

}
