package com.arch.micro_service.chat_server.chatter.application.usecase;

import java.util.List;

import com.arch.micro_service.chat_server.chatter.domain.entity.Chatter;
import com.arch.micro_service.chat_server.chatter.domain.exception.ChatterException;
import com.arch.micro_service.chat_server.chatter.infrastructure.persistence.ChatterRepository;
import com.arch.micro_service.chat_server.logger.CustomLogger;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatterFindUseCase {

  private final ChatterRepository chatterRepository;
  private final CustomLogger log;

  public Chatter findById(Long id) {

    var chatter = chatterRepository.findById(id).orElseThrow(() -> {
      var ex = ChatterException.notFound();
      log.failure("Chatter not found " + id, ex);
      return ex;
    });

    if (chatter.isDeleted()) {
      var ex = ChatterException.notFound();
      log.failure("Chatter not found " + id, ex);
      throw ex;
    }

    return chatter;
  }

}
