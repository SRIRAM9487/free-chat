package com.arch.micro_service.auth_server.user.infrastructure.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.arch.micro_service.auth_server.role.domain.etntiy.Role;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.mapper.RoleMapper;
import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RoleUserMetaDataResponse;
import com.arch.micro_service.auth_server.shared.domain.constant.Gender;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.vo.Email;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserCreateRequest;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserDetailResponse;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserLoginResponse;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

  private final RoleMapper roleMapper;

  public UserDetailResponse fromUser(User user) {
    List<RoleUserMetaDataResponse> roles = new ArrayList<>();
    for (Role role : user.getRoles()) {
      roles.add(roleMapper.toRoleUserMetaData(role));
    }
    return new UserDetailResponse(
        user.getId().toString(),
        user.getName(),
        user.getUserName(),
        user.getEmail().value(),
        user.getEmail().emailVerified(),
        user.getGender().name(),
        user.isAccountNonExpired(),
        user.isAccountNonLocked(),
        user.isEnabled(),
        roles);
  }

  public void update(User user, UserCreateRequest requestDto) {

    if (requestDto.name() != null) {
      user.setName(requestDto.name());
    }

    if (requestDto.userName() != null) {
      user.setUserName(requestDto.userName());
    }

    if (requestDto.email() != null) {
      user.setEmail(Email.create(requestDto.email()));
    }

    if (requestDto.gender() != null) {
      user.setGender(Gender.valueOf(requestDto.gender()));
    }

    user.setAccountNonExpired(requestDto.accountNonExpired());

    user.setAccountNonLocked(requestDto.accountNonLocked());

    user.setEnabled(requestDto.enabled());
  }

  public User toUser(UserCreateRequest requestDto) {
    User user = User
        .builder()
        .userName(requestDto.userName())
        .email(Email.create(requestDto.email()))
        .gender(Gender.valueOf(requestDto.gender()))
        .accountNonExpired(requestDto.accountNonExpired())
        .accountNonLocked(requestDto.accountNonLocked())
        .enabled(requestDto.enabled())
        .build();
    user.setName(requestDto.name());
    return user;
  }

  public UserLoginResponse toUserLoginResponse(User user, String token) {
    List<String> roles = new ArrayList<>();
    for (Role role : user.getRoles()) {
      roles.add(role.getTitle());
    }
    return new UserLoginResponse(user.getId().toString(), user.getUserName(), roles, token);
  }
}
