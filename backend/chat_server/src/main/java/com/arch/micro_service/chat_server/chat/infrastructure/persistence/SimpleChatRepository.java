package com.arch.micro_service.chat_server.chat.infrastructure.persistence;

import com.arch.micro_service.chat_server.chat.domain.entity.SimpleChat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleChatRepository extends JpaRepository<SimpleChat, Long> {

}
