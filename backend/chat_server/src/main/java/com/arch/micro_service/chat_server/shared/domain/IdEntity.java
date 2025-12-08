package com.arch.micro_service.chat_server.shared.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Id Entity
 */
@Getter
@Setter
public abstract class IdEntity extends SoftDelete {

  /**
   * Id
   */
  private Long id;

}
