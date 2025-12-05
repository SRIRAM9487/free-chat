package com.arch.micro_service.chat_server.group.infrastructure.persistence;

import com.arch.micro_service.chat_server.group.domain.entity.Group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
