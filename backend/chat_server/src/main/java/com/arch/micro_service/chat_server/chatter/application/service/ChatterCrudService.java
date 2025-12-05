package com.arch.micro_service.chat_server.chatter.application.service;

import java.util.List;

import com.arch.micro_service.chat_server.chatter.domain.entity.Chatter;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.request.ChatterCreateRequest;

public interface ChatterCrudService {

  List<Chatter> getAll();

  Chatter get(String id);

  Chatter create(ChatterCreateRequest requestDto);

  Chatter update(String id, ChatterCreateRequest requestDto);

  Chatter delete(String id);

}
