package com.arch.micro_service.auth_server.role.infrastructure.persistence;

import java.util.Optional;

import com.arch.micro_service.auth_server.role.domain.etntiy.Permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

  Optional<Permission> findByTitle(String title);

}
