package com.arch.micro_service.chat_server.websocket.controller;

import java.time.LocalDateTime;

import com.arch.micro_service.chat_server.chat.application.service.ChatService;
import com.arch.micro_service.chat_server.chat.infrastructure.dto.request.ChatMessageCreateRequest;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.dto.response.ChatMessage;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

  private final ChatService chatService;

  @MessageMapping("/chat-group/message/{chatgroupId}")
  @SendTo("/topic/chat-group/message/{chatgroupId}")
  public ChatMessage chatMessage(
      @DestinationVariable String chatgroupId,
      @RequestBody ChatMessageCreateRequest request) {
    chatService.create(request);
    return new ChatMessage(request.message(), request.chatter(), LocalDateTime.now());
  }

}
