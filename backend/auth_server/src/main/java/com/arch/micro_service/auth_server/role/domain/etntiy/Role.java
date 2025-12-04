package com.arch.micro_service.auth_server.role.domain.etntiy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.arch.micro_service.auth_server.shared.domain.entity.IdEntity;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "roles")
@ToString

public class Role extends IdEntity {

  @Column(name = "title", nullable = false, unique = true)
  private String title;

  @Column(name = "active", nullable = false)
  private boolean active;

  @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @Builder.Default
  @JsonManagedReference
  private List<RolePermission> rolePermissions = new ArrayList<>();

  @ManyToMany
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  @Builder.Default
  @ToString.Exclude
  private Set<User> users = new HashSet<>();

  public void toggleActive() {
    this.active = !this.active;
  }
}
