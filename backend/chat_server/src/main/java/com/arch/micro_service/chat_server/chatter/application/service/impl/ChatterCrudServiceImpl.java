package com.arch.micro_service.chat_server.chatter.application.service.impl;

import java.util.List;

import com.arch.micro_service.chat_server.chatter.application.service.ChatterCrudService;
import com.arch.micro_service.chat_server.chatter.domain.entity.Chatter;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.request.ChatterCreateRequest;
import com.arch.micro_service.chat_server.chatter.infrastructure.persistence.ChatterRepository;
import com.arch.micro_service.chat_server.logger.context.MetaContextHolder;
import com.arch.micro_service.chat_server.logger.event.LogSuccessEvent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatterCrudServiceImpl implements ChatterCrudService {

  private final ChatterRepository chatterRepository;
  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public List<Chatter> findAll() {
    return chatterRepository.findAll();
  }

  @Override
  public Chatter findById(Long id) {
    return chatterRepository.findById(id);
  }

  @Override
  public Chatter create(ChatterCreateRequest request) {
    var chatter = new Chatter();
    chatter.setName(request.name());
    chatter.setUserId(request.userId());
    chatter.setCreatedBy(MetaContextHolder.get().getUserId());
    Chatter savedChatter = chatterRepository.save(chatter);
    applicationEventPublisher.publishEvent(new LogSuccessEvent("Chatter Created", "New", savedChatter, this));
    return savedChatter;
  }

  @Override
  public Chatter update(Long id, ChatterCreateRequest request) {
    var chatter = new Chatter();
    chatter.setId(id);
    chatter.setName(request.name());
    chatter.setUserId(request.userId());
    chatter.setUpdatedBy(MetaContextHolder.get().getUserId());
    Chatter updatedChatter = chatterRepository.update(chatter);
    applicationEventPublisher.publishEvent(new LogSuccessEvent("Chatter Updated", chatter, updatedChatter, this));
    return updatedChatter;
  }

  @Override
  public Chatter delete(Long id) {
    Chatter deletedChatter = chatterRepository.delete(id);
    applicationEventPublisher.publishEvent(new LogSuccessEvent("Chatter Deleted", "Deleted", deletedChatter, this));
    return deletedChatter;
  }

}
