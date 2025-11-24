package com.arch.micro_service.auth_server.shared.domain.entity;

import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Id Entity
 */
@MappedSuperclass
@Getter
@Setter
public abstract class IdEntity extends SoftDelete {

  /**
   * Id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

}
