package com.arch.micro_service.auth_server.user.domain.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * For spring securiy User principal
 * 
 * @author SRIRAM
 */
public class UserImpl implements UserDetails {

  private final List<SimpleGrantedAuthority> authorities;
  private User user;

  public UserImpl(User user, List<SimpleGrantedAuthority> authorities) {
    this.user = user;
    this.authorities = authorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
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
