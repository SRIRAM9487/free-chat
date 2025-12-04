package com.arch.micro_service.auth_server.role.persistence.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arch.micro_service.auth_server.log.CustomLogger;
import com.arch.micro_service.auth_server.role.application.constant.RoleConstant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RoleCrudController {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private CustomLogger customLogger;

  @BeforeEach
  void setup() {
    doNothing().when(customLogger).success(anyString(), anyString(), any(), any());
    doNothing().when(customLogger).failure(anyString(), anyString(), any());
  }

  @Test
  @WithMockUser(authorities = "ROLE_READ")
  void getAll() throws Exception {
    this.mockMvc
        .perform(get("/v1/role").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").isArray())
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @WithMockUser(authorities = "ROLE_READ")
  void getById() throws Exception {
    this.mockMvc
        .perform(get("/v1/role/1").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data.id").value(1L))
        .andExpect(jsonPath("$.data.title").value("SUDO"))
        .andExpect(jsonPath("$.data.active").value(true))
        .andExpect(jsonPath("$.data.rolePermissions").isArray())
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @WithMockUser(authorities = "ROLE_CREATE")
  void createRole() throws Exception {
    String body = """
        {
          "title": "TEST_ROLE",
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
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(RoleConstant.CREATE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @WithMockUser(authorities = "ROLE_UPDATE")
  void updateRole() throws Exception {
    String body = """
        {
          "title": "UPDATED_TEST_ROLE",
          "active": false,
          "rolePermissions": [
            { "permissionId": "1", "active": false },
            { "permissionId": "2", "active": true },
            { "permissionId": "3", "active": false }
          ]
        }
        """;

    this.mockMvc.perform(patch("/v1/role/update/1")
        .content(body)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(RoleConstant.UPDATE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @WithMockUser(authorities = "ROLE_DELETE")
  void deleteRole() throws Exception {
    this.mockMvc.perform(delete("/v1/role/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(RoleConstant.DELETE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

}
