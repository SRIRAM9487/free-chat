package com.arch.micro_service.auth_server.user.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import com.arch.micro_service.auth_server.user.domain.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByEmail_Value(String email);

  Optional<User> findByUserName(String userName);
}
