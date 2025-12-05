package com.arch.micro_service.chat_server.shared.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Named Entity
 */
@MappedSuperclass
@Getter
@Setter
public class NamedEntity extends SoftDelete {

  /**
   * Name field
   */
  @Column(name = "name")
  private String name;

}
