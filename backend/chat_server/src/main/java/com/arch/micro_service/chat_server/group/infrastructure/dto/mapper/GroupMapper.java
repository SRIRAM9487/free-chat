package com.arch.micro_service.chat_server.group.infrastructure.dto.mapper;

import com.arch.micro_service.chat_server.group.domain.entity.Group;
import com.arch.micro_service.chat_server.group.domain.entity.GroupMember;
import com.arch.micro_service.chat_server.group.infrastructure.dto.request.GroupCreateRequest;
import com.arch.micro_service.chat_server.group.infrastructure.dto.response.GroupDetailsResponse;
import com.arch.micro_service.chat_server.group.infrastructure.dto.response.GroupMemberDetailsResponse;

import org.springframework.stereotype.Component;

@Component
public class GroupMapper {

  public Group toGroup(GroupCreateRequest createRequest) {
    var group = Group
        .builder()
        .description(createRequest.description())
        .build();
    group.setName(createRequest.name());
    group.setAdminOnly(createRequest.adminOnly());
    return group;
  }

  public void update(Group group, GroupCreateRequest createRequest) {
    group.setName(createRequest.name());
    group.setDescription(createRequest.description());
    group.setAdminOnly(createRequest.adminOnly());
  }

  public GroupDetailsResponse fromGroup(Group group) {
    var members = group.getGroupMembers().stream().map(this::fromGroupMember).toList();
    var response = new GroupDetailsResponse(
        group.getId(),
        group.getName(),
        group.getDescription(),
        members,
        group.isAdminOnly());
    return response;
  }

  private GroupMemberDetailsResponse fromGroupMember(GroupMember groupMember) {
    return new GroupMemberDetailsResponse(
        groupMember.getId(),
        groupMember.getChatter().getId(),
        groupMember.getAccessLevel().name());
  }

}
