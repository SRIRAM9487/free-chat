package com.arch.micro_service.auth_server.role.domain.etntiy;

import com.arch.micro_service.auth_server.shared.domain.entity.IdEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "role_permission")
@ToString
public class RolePermission extends IdEntity {

  @Column(name = "active")
  private boolean active;

  @Column(name = "active_status")
  private boolean activeStatus;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "role_id", referencedColumnName = "id")
  @ToString.Exclude
  private Role role;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "permission_id", referencedColumnName = "id")
  @ToString.Exclude
  private Permission permission;

}
