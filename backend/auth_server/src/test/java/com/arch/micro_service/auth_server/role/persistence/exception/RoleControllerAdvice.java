package com.arch.micro_service.auth_server.role.persistence.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arch.micro_service.auth_server.role.domain.exception.type.RoleExceptionType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RoleControllerAdvice {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(authorities = "ROLE_VIEW")
  void getById() throws Exception {
    this.mockMvc.perform(get("/v1/role/9999")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.GET.name()))
        .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.path").value("/v1/role/9999"))
        .andExpect(jsonPath("$.message").value(RoleExceptionType.ROLE_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(RoleExceptionType.ROLE_NOT_FOUND.name()));
  }

  @Test
  @WithMockUser(authorities = "ROLE_CREATE")
  void createRole() throws Exception {
    String body = """
        {
          "title": "ADMIN",
          "active": true,
          "rolePermissions": [
            { "permissionId": "1", "active": true },
            { "permissionId": "2", "active": true },
            { "permissionId": "3", "active": false }
          ]
        }
        """;

    this.mockMvc.perform(post("/v1/role/create")
        .content(body)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.POST.name()))
        .andExpect(status().is(HttpStatus.CONFLICT.value()))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.path").value("/v1/role/create"))
        .andExpect(jsonPath("$.message").value(RoleExceptionType.ROLE_TITLE_UNIQUE.getMessage()))
        .andExpect(jsonPath("$.code").value(RoleExceptionType.ROLE_TITLE_UNIQUE.name()));
  }

  @Test
  @WithMockUser(authorities = "ROLE_UPDATE")
  void updateRoleNotFound() throws Exception {
    String body = """
        {
          "title": "ADMIN",
          "active": true,
          "rolePermissions": [
            { "permissionId": "1", "active": true },
            { "permissionId": "2", "active": true },
            { "permissionId": "3", "active": false }
          ]
        }
        """;

    this.mockMvc.perform(patch("/v1/role/update/9999")
        .content(body)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.PATCH.name()))
        .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.path").value("/v1/role/update/9999"))
        .andExpect(jsonPath("$.message").value(RoleExceptionType.ROLE_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(RoleExceptionType.ROLE_NOT_FOUND.name()));
  }

  @Test
  @WithMockUser(authorities = "ROLE_DELETE")
  void deleteRoleNotFound() throws Exception {

    this.mockMvc.perform(delete("/v1/role/9999")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.DELETE.name()))
        .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.path").value("/v1/role/9999"))
        .andExpect(jsonPath("$.message").value(RoleExceptionType.ROLE_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(RoleExceptionType.ROLE_NOT_FOUND.name()));
  }
}
