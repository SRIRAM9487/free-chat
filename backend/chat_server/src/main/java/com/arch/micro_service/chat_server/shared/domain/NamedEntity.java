package com.arch.micro_service.chat_server.shared.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Named Entity
 */
@Getter
@Setter
public class NamedEntity extends SoftDelete {

  /**
   * Name field
   */
  private String name;

}
