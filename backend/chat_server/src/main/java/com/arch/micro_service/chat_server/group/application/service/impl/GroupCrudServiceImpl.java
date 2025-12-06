package com.arch.micro_service.chat_server.group.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.arch.micro_service.chat_server.chatter.application.usecase.ChatterFindUseCase;
import com.arch.micro_service.chat_server.group.application.service.GroupCrudService;
import com.arch.micro_service.chat_server.group.application.usecase.GroupFindUseCase;
import com.arch.micro_service.chat_server.group.domain.entity.Group;
import com.arch.micro_service.chat_server.group.domain.entity.GroupMember;
import com.arch.micro_service.chat_server.group.infrastructure.dto.mapper.GroupMapper;
import com.arch.micro_service.chat_server.group.infrastructure.dto.request.GroupCreateRequest;
import com.arch.micro_service.chat_server.group.infrastructure.persistence.GroupRepository;
import com.arch.micro_service.chat_server.logger.CustomLogger;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GroupCrudServiceImpl implements GroupCrudService {

  private final GroupRepository groupRepository;
  private final GroupFindUseCase groupFindUseCase;
  private final GroupMapper groupMapper;
  private final ChatterFindUseCase chatterFindUseCase;
  private final CustomLogger customLogger;

  @Override
  public List<Group> getAll() {
    return groupRepository.findAll().stream().filter(c -> !c.isDeleted()).toList();
  }

  @Override
  public Group get(String id) {
    return groupFindUseCase.findById(Long.parseLong(id));
  }

  @Override
  public Group create(GroupCreateRequest requestDto) {
    Group group = groupMapper.toGroup(requestDto);

    List<GroupMember> groupMembers = new ArrayList<>();
    for (var groupId : requestDto.groupMemberIds()) {
      var chatter = chatterFindUseCase.findById(groupId.chatterId());
      var member = GroupMember
          .builder()
          .chatter(chatter)
          .group(group)
          .build();
      groupMembers.add(member);
    }
    group.setGroupMembers(groupMembers);

    var savedGroup = groupRepository.save(group);
    customLogger.success("Group created", savedGroup, "NEW");
    return savedGroup;
  }

  @Override
  public Group update(String id, GroupCreateRequest requestDto) {
    Group group = groupFindUseCase.findById(Long.parseLong(id));
    groupMapper.update(group, requestDto);
    List<GroupMember> groupMembers = new ArrayList<>();
    for (var groupId : requestDto.groupMemberIds()) {
      var chatter = chatterFindUseCase.findById(groupId.chatterId());
      var member = GroupMember
          .builder()
          .chatter(chatter)
          .group(group)
          .build();
      groupMembers.add(member);
    }
    group.getGroupMembers().clear();
    group.getGroupMembers().addAll(groupMembers);
    var updatedGroup = groupRepository.save(group);
    customLogger.success("Group updated", updatedGroup, group);
    return updatedGroup;
  }

  @Override
  public Group delete(String id) {
    Group group = groupFindUseCase.findById(Long.parseLong(id));
    group.softDelete();
    for (var grp : group.getGroupMembers()) {
      group.getGroupMembers().remove(grp);
    }
    var deletedGroup = groupRepository.save(group);
    customLogger.success("Group deleted", deletedGroup, group);
    return deletedGroup;
  }

}
