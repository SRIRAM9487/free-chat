package com.arch.micro_service.auth_server.role.domain.etntiy;

import com.arch.micro_service.auth_server.shared.domain.entity.IdEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@Getter
@Setter
@Table(name = "access")
@ToString
public class Access extends IdEntity {

  private boolean view;

  private boolean create;

  private boolean update;

  private boolean delete;

}
