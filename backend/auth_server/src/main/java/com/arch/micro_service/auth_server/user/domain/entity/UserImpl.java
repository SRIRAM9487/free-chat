package com.arch.micro_service.auth_server.user.domain.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * For spring securiy User principal
 * 
 * @author SRIRAM
 */
public class UserImpl implements UserDetails {

  private User user;

  public UserImpl(User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getUsername() {
    return user.getUserName();
  }

  @Override
  public String getPassword() {
    return user.getPassword().value();
  }

  @Override
  public boolean isAccountNonExpired() {
    return user.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return user.isAccountNonLocked();
  }

  @Override
  public boolean isEnabled() {
    return user.isEnabled();
  }

  public Long getId() {
    return user.getId();
  }
}
