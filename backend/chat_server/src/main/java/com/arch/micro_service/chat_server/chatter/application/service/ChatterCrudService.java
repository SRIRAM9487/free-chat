package com.arch.micro_service.chat_server.chatter.application.service;

import java.util.List;

import com.arch.micro_service.chat_server.chatter.domain.entity.Chatter;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.request.ChatterCreateRequest;

public interface ChatterCrudService {

  List<Chatter> findAll();

  Chatter findById(Long id);

  Chatter create(ChatterCreateRequest request);

  Chatter update(Long id, ChatterCreateRequest request);

  Chatter delete(Long id);

}
