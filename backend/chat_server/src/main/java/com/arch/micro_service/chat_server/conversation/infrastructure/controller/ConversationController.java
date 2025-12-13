package com.arch.micro_service.chat_server.conversation.infrastructure.controller;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.ConversationChatGroupDto;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.ConversationChatterDto;
import com.arch.micro_service.chat_server.chatter.infrastructure.dto.response.ConversationsSummaryDto;
import com.arch.micro_service.chat_server.conversation.infrastructure.persistence.ConversationSummaryRepository;
import com.arch.micro_service.chat_server.shared.infrastructure.dto.api.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/v1/conversation")
@RestController
@RequiredArgsConstructor
public class ConversationController {

  private final ConversationSummaryRepository conversationSummaryRepository;

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<ConversationsSummaryDto>> getAll(@PathVariable("id") Long id) {
    List<ConversationChatterDto> single = conversationSummaryRepository.getAllChatterForId(id);
    List<ConversationChatGroupDto> group = conversationSummaryRepository.getAllChatterGroupForId(id);
    var response = ApiResponse.create(new ConversationsSummaryDto(single, group));
    return ResponseEntity.ok(response);
  }

}
