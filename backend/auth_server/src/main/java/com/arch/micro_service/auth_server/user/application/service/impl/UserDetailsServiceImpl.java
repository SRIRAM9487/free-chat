package com.arch.micro_service.auth_server.user.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.arch.micro_service.auth_server.user.application.usecase.UserFindUseCase;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.entity.UserImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserFindUseCase userFindUseCase;
  private final Logger log = LoggerFactory.getLogger("MethodLogger");

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.trace("Authenticating User : {}", username);
    User existingUser = userFindUseCase.findByUserId(username);

    var roles = existingUser.getRoles();

    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    for (var role : roles) {
      for (var rolePermission : role.getRolePermissions()) {
        var permission = rolePermission.getPermission();
        if (rolePermission.isActive() && permission.isActive()) {
          String title = permission.getTitle();
          log.trace("Authority applied {}", title);
          authorities.add(new SimpleGrantedAuthority(title));
        }
      }
    }

    log.trace("User found : {} ", username);
    log.trace("User Authorities : {} ", authorities);
    return new UserImpl(existingUser, authorities);
  }
}
