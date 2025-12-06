package com.arch.micro_service.chat_server.group.infrastructure.persistence;

import com.arch.micro_service.chat_server.group.domain.entity.GroupMember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

}
