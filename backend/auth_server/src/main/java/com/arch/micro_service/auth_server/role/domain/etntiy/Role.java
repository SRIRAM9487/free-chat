package com.arch.micro_service.auth_server.role.domain.etntiy;

import com.arch.micro_service.auth_server.shared.domain.entity.BasedEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "roles")
@ToString
public class Role extends BasedEntity {

  @Column(name = "title", nullable = false, unique = true)
  private String title;

}
