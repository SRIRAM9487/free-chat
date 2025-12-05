package com.arch.micro_service.chat_server.shared.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Base entity
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BasedEntity extends IdEntity {

  /**
   * Name field
   */
  @Column(name = "name")
  private String name;

}
