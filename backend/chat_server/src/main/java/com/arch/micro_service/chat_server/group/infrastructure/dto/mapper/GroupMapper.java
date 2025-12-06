package com.arch.micro_service.chat_server.group.infrastructure.dto.mapper;

import com.arch.micro_service.chat_server.group.domain.entity.Group;
import com.arch.micro_service.chat_server.group.infrastructure.dto.request.GroupCreateRequest;
import com.arch.micro_service.chat_server.group.infrastructure.dto.response.GroupDetailsResponse;

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
    return new GroupDetailsResponse(group.getId(), group.getName(), group.getDescription(), group.isAdminOnly());
  }

}
