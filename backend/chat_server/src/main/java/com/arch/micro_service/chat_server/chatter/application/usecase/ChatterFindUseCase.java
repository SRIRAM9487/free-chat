package com.arch.micro_service.chat_server.chatter.application.usecase;

import com.arch.micro_service.chat_server.chatter.domain.entity.Chatter;
import com.arch.micro_service.chat_server.chatter.domain.exception.ChatterException;
import com.arch.micro_service.chat_server.chatter.infrastructure.persistence.ChatterRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatterFindUseCase {

  private final ChatterRepository chatterRepository;

  public Chatter findById(Long id) {

    var chatter = chatterRepository.findById(id).orElseThrow(() -> {
      return ChatterException.notFound();
    });

    if (chatter.isDeleted()) {
      var ex = ChatterException.notFound();
      throw ex;
    }

    return chatter;
  }
}
