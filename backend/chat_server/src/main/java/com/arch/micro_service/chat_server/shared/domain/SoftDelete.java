package com.arch.micro_service.chat_server.shared.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * Enable soft-delete
 * All entity should extend
 */
@Getter
@Setter
public abstract class SoftDelete {

  /*
   * entity created at timestamp
   */
  private LocalDateTime createdAt;

  /*
   * user who created the entity
   */
  private String createdBy;

  /*
   * entity updated at timestamp
   */
  private LocalDateTime updatedAt;

  /*
   * user who updated the entity
   */
  private String updatedBy;

  /*
   * entity deleted at timestamp
   */
  private LocalDateTime deletedAt;

  /*
   * user who deleted the entity
   */
  private String deletedBy;

  /*
   * new entity creation method
   */
  public void init() {
    this.createdAt = LocalDateTime.now();
  }

  /*
   * new entity update method
   */
  public void update() {
    this.updatedAt = LocalDateTime.now();
  }

  /*
   * soft delete the entity
   */
  public void softDelete() {
    this.deletedAt = LocalDateTime.now();
  }

  /*
   * Is soft deleted
   */
  public boolean isDeleted() {
    return this.deletedAt != null;
  }

}
