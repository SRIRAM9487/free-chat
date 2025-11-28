package com.arch.micro_service.auth_server.user.infrastructure.dto.response;

import java.util.List;

import com.arch.micro_service.auth_server.role.infrastructure.dto.role.response.RoleUserMetaDataResponse;

public record UserDetailResponse(
    String id,
    String name,
    String userName,
    String email,
    boolean emailVerified,
    String gender,
    boolean accountNonExpired,
    boolean accountNonLocked,
    boolean enabled,
    List<RoleUserMetaDataResponse> roles) {

}
