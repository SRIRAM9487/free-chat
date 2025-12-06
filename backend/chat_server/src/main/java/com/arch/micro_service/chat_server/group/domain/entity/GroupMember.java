package com.arch.micro_service.chat_server.group.domain.entity;

import com.arch.micro_service.chat_server.chatter.domain.entity.Chatter;
import com.arch.micro_service.chat_server.shared.domain.IdEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "group_member")
public class GroupMember extends IdEntity {

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "chatters_id", referencedColumnName = "id")
  private Chatter chatter;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "group_id", referencedColumnName = "id")
  private Group group;

  @Column(name = "access_level")
  @Enumerated(EnumType.STRING)
  private AccessLevel accessLevel;

}
