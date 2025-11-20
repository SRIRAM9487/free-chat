package com.arch.micro_service.auth_server.shared.domain.entity;

import jakarta.persistence.Column;

/**
 * Base entity
 */
public abstract class BasedEntity extends IdEntity {

  /**
   * Name field
   */
  @Column(name = "name")
  private String name;

}
