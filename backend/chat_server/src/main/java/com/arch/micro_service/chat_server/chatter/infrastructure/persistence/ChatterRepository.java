package com.arch.micro_service.chat_server.chatter.infrastructure.persistence;

import java.util.List;

import com.arch.micro_service.chat_server.chatter.domain.entity.Chatter;

public interface ChatterRepository {

  List<Chatter> findAll();

  Chatter findById(Long id);

  Chatter save(Chatter chatGroup);

  Chatter update(Chatter chatGroup);

  Chatter delete(Long id);

}
