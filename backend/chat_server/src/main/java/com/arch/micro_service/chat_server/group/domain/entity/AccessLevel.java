package com.arch.micro_service.chat_server.group.domain.entity;

public enum AccessLevel {

  CREATOR, ADMIN, MEMBER;

  public boolean isCreator() {
    return this == CREATOR;
  }

  public boolean isAdmin() {
    return this == ADMIN;
  }

  public boolean isMember() {
    return this == MEMBER;
  }
}
