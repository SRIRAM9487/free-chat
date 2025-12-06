package com.arch.micro_service.chat_server.shared.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

/**
 * Enable soft-delete
 * All entity should extend
 */
@MappedSuperclass
@Getter
@Setter
public abstract class SoftDelete {

  /*
   * entity created at timestamp
   */
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  /*
   * user who created the entity
   */
  @Column(name = "created_by")
  private String createdBy;

  /*
   * entity updated at timestamp
   */
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  /*
   * user who updated the entity
   */
  @Column(name = "updated_by")
  private String updatedBy;

  /*
   * entity deleted at timestamp
   */
  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;

  /*
   * user who deleted the entity
   */
  @Column(name = "deleted_By")
  private String deletedBy;

  /*
   * new entity creation method
   */
  @PrePersist
  public void init() {
    this.createdAt = LocalDateTime.now();
  }

  /*
   * new entity update method
   */
  @PreUpdate
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
