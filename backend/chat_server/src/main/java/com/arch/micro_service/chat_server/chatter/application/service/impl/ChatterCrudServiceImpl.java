package com.arch.micro_service.chat_server.chatter.application.service.impl;

import java.util.List;

import com.arch.micro_service.chat_server.chatter.application.service.ChatterCrudService;
import com.arch.micro_service.chat_server.chatter.application.usecase.ChatterFindUseCase;
import com.arch.micro_service.chat_server.chatter.domain.entity.Chatter;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.mapper.ChatterMapper;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.request.ChatterCreateRequest;
import com.arch.micro_service.chat_server.chatter.infrastructure.persistence.ChatterRepository;
import com.arch.micro_service.chat_server.logger.CustomLogger;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatterCrudServiceImpl implements ChatterCrudService {

  private final ChatterRepository chatterRepository;
  private final ChatterFindUseCase chatterFindUseCase;
  private final ChatterMapper chatterMapper;
  private final CustomLogger customLogger;

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
    customLogger.success("Chatter created", savedChatter, "NEW");
    return savedChatter;
  }

  @Override
  public Chatter update(String id, ChatterCreateRequest requestDto) {
    var chatter = chatterFindUseCase.findById(Long.valueOf(id));
    chatterMapper.update(chatter, requestDto);
    var updatedChatter = chatterRepository.save(chatter);
    customLogger.success("Chatter updated", updatedChatter, chatter);
    return updatedChatter;
  }

  @Override
  public Chatter delete(String id) {
    var chatter = chatterFindUseCase.findById(Long.valueOf(id));
    chatter.softDelete();
    var deletedChatter = chatterRepository.save(chatter);
    customLogger.success("Chatter deleted", deletedChatter, chatter);
    return deletedChatter;
  }

}
