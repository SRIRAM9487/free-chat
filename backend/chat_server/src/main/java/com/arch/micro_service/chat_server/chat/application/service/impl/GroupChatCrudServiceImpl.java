package com.arch.micro_service.chat_server.chat.application.service.impl;

import java.util.List;

import com.arch.micro_service.chat_server.chat.application.service.GroupChatCrudService;
import com.arch.micro_service.chat_server.chat.application.usecase.GroupChatUseCase;
import com.arch.micro_service.chat_server.chat.domain.entity.GroupChat;
import com.arch.micro_service.chat_server.chat.infrastructure.dto.request.GroupChatCreateRequest;
import com.arch.micro_service.chat_server.chat.infrastructure.persistence.GroupChatRepository;
import com.arch.micro_service.chat_server.group.application.usecase.GroupMemberFindUseCase;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupChatCrudServiceImpl implements GroupChatCrudService {

  private final GroupChatRepository groupChatRepository;;
  private final GroupChatUseCase groupChatUseCase;
  private final GroupMemberFindUseCase groupMemberFindUseCase;

  @Override
  public List<GroupChat> getAll() {
    return groupChatRepository.findAll().stream().filter(c -> !c.isDeleted()).toList();
  }

  @Override
  public GroupChat get(String id) {
    return groupChatUseCase.findById(Long.parseLong(id));
  }

  @Override
  public GroupChat create(GroupChatCreateRequest requestDto) {
    var chat = new GroupChat();
    chat.setMessage(requestDto.message());
    var group = groupMemberFindUseCase.findById(Long.parseLong(requestDto.groupId()));
    chat.setGroupMembers(group);
    var createGroupChat = groupChatRepository.save(chat);
    return createGroupChat;
  }

  @Override
  public GroupChat update(String id, GroupChatCreateRequest requestDto) {
    var chat = groupChatUseCase.findById(Long.parseLong(id));
    var group = groupMemberFindUseCase.findById(Long.parseLong(requestDto.groupId()));
    chat.setMessage(requestDto.message());
    chat.setGroupMembers(group);
    var updateGroupChat = groupChatRepository.save(chat);
    return updateGroupChat;
  }

  @Override
  public GroupChat delete(String id) {
    var chat = groupChatUseCase.findById(Long.parseLong(id));
    chat.softDelete();
    var deleteGroupChat = groupChatRepository.save(chat);
    return deleteGroupChat;
  }

}
