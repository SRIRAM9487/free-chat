package com.arch.micro_service.chat_server.group.application.service.impl;

import java.util.List;

import com.arch.micro_service.chat_server.group.application.service.GroupCrudService;
import com.arch.micro_service.chat_server.group.application.usecase.GroupFindUseCase;
import com.arch.micro_service.chat_server.group.domain.entity.Group;
import com.arch.micro_service.chat_server.group.infrastructure.dto.mapper.GroupMapper;
import com.arch.micro_service.chat_server.group.infrastructure.dto.request.GroupCreateRequest;
import com.arch.micro_service.chat_server.group.infrastructure.persistence.GroupRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GroupCrudServiceImpl implements GroupCrudService {

  private final GroupRepository groupRepository;
  private final GroupFindUseCase groupFindUseCase;
  private final GroupMapper groupMapper;

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
    var savedGroup = groupRepository.save(group);
    return savedGroup;
  }

  @Override
  public Group update(String id, GroupCreateRequest requestDto) {
    Group group = groupFindUseCase.findById(Long.parseLong(id));
    groupMapper.update(group, requestDto);
    var updatedGroup = groupRepository.save(group);
    return updatedGroup;
  }

  @Override
  public Group delete(String id) {
    Group group = groupFindUseCase.findById(Long.parseLong(id));
    group.softDelete();
    var deletedGroup = groupRepository.save(group);
    return deletedGroup;
  }

}
