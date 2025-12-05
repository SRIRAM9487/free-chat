package com.arch.micro_service.chat_server.chatter.application.service.impl;

import java.util.List;

import com.arch.micro_service.chat_server.chatter.application.service.ChatterCrudService;
import com.arch.micro_service.chat_server.chatter.application.usecase.ChatterFindUseCase;
import com.arch.micro_service.chat_server.chatter.domain.entity.Chatter;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.mapper.ChatterMapper;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.request.ChatterCreateRequest;
import com.arch.micro_service.chat_server.chatter.infrastructure.persistence.ChatterRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatterCrudServiceImpl implements ChatterCrudService {

  private final ChatterRepository chatterRepository;
  private final ChatterFindUseCase chatterFindUseCase;
  private final ChatterMapper chatterMapper;

  @Override
  public List<Chatter> getAll() {
    return chatterRepository.findAll().stream().filter(c -> !c.isDeleted()).toList();
  }

  @Override
  public Chatter get(String id) {
    return chatterFindUseCase.findById(Long.valueOf(id));
  }

  @Override
  public Chatter create(ChatterCreateRequest requestDto) {
    var chatter = chatterMapper.toChatter(requestDto);
    var savedChatter = chatterRepository.save(chatter);
    return savedChatter;
  }

  @Override
  public Chatter update(String id, ChatterCreateRequest requestDto) {
    var existingChatter = chatterFindUseCase.findById(Long.valueOf(id));
    chatterMapper.update(existingChatter, requestDto);
    var updatedChatter = chatterRepository.save(existingChatter);
    return updatedChatter;
  }

  @Override
  public Chatter delete(String id) {
    var existingChatter = chatterFindUseCase.findById(Long.valueOf(id));
    existingChatter.softDelete();
    var deletedChatter = chatterRepository.save(existingChatter);
    return deletedChatter;
  }

}
