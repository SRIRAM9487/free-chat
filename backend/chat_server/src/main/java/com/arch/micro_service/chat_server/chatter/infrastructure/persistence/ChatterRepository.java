package com.arch.micro_service.chat_server.chatter.infrastructure.persistence;

import com.arch.micro_service.chat_server.chatter.domain.entity.Chatter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatterRepository extends JpaRepository<Chatter, Long> {
}
