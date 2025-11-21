package com.arch.micro_service.auth_server.role.infrastructure.persistence;

import java.util.UUID;

import com.arch.micro_service.auth_server.role.domain.etntiy.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

}
