package com.arch.micro_service.chat_server.chatter.infrastructure.dto.mapper;

import com.arch.micro_service.chat_server.chatter.domain.entity.Chatter;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.request.ChatterCreateRequest;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.response.ChatterDetailsResponse;

import org.springframework.stereotype.Component;

@Component
public class ChatterMapper {

  public Chatter toChatter(ChatterCreateRequest createRequest) {
    var chatter = Chatter
        .builder()
        .userId(createRequest.userId())
        .build();
    chatter.setName(createRequest.name());
    return chatter;
  }

  public void update(Chatter chatter, ChatterCreateRequest createRequest) {
    chatter.setUserId(createRequest.userId());
    chatter.setName(createRequest.name());
  }

  public ChatterDetailsResponse fromChatter(Chatter chatter) {
    var resp = new ChatterDetailsResponse(
        chatter.getId(),
        chatter.getUserId(),
        chatter.getName());
    return resp;
  }
}
