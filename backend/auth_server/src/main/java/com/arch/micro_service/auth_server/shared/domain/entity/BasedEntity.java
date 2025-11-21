package com.arch.micro_service.auth_server.shared.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

/**
 * Base entity
 */
@MappedSuperclass
@Getter
public abstract class BasedEntity extends IdEntity {

  /**
   * Name field
   */
  @Column(name = "name")
  private String name;

}
