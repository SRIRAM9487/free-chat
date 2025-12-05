package com.arch.micro_service.chat_server.group.domain.entity;

import java.util.List;

import com.arch.micro_service.chat_server.shared.domain.BasedEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "groups")
public class Group extends BasedEntity {

  @Column(name = "description")
  private String description;

  @OneToMany(mappedBy = "group")
  private List<GroupMember> groupMembers;

  @Column(name = "admin_only")
  private boolean adminOnly;

}
