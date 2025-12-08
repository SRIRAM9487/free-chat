package com.arch.micro_service.chat_server.shared.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Base entity
 */
@Getter
@Setter
public abstract class BasedEntity extends IdEntity {

  /**
   * Name field
   */
  private String name;

}
