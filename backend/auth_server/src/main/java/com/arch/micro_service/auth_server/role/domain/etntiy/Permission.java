package com.arch.micro_service.auth_server.role.domain.etntiy;

import java.util.List;

import com.arch.micro_service.auth_server.shared.domain.entity.IdEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@Table(name = "permissions")
@ToString
public class Permission extends IdEntity {

  @Column(name = "title", nullable = false, unique = true)
  private String title;

  @Column(name = "active")
  private boolean active;

  @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<RolePermission> rolePermission;

}
