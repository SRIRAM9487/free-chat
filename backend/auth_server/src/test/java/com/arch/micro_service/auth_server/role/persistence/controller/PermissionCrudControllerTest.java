package com.arch.micro_service.auth_server.role.persistence.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.arch.micro_service.auth_server.role.application.constant.PermissionConstant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PermissionCrudControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(authorities = "PERMISSION_VIEW")
  void getAll() throws Exception {
    mockMvc.perform(get("/v1/permission")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").isArray())
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @WithMockUser(authorities = "PERMISSION_VIEW")
  void getById() throws Exception {
    mockMvc.perform(get("/v1/permission/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data.id").value(1))
        .andExpect(jsonPath("$.data.title").value("ROLE_CREATE"))
        .andExpect(jsonPath("$.data.active").value(true))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @WithMockUser(authorities = "PERMISSION_CREATE")
  void create() throws Exception {
    String body = """
        {
        "title" : "TEST_CREATE_PERMISSION",
        "active" : true
        }
        """;
    mockMvc.perform(post("/v1/permission/create")
        .content(body)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(PermissionConstant.CREATE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @WithMockUser(authorities = "PERMISSION_UPDATE")
  void update() throws Exception {
    String body = """
        {
        "title" : "TEST_CREATE_PERMISSION",
        "active" : true
        }
        """;
    mockMvc.perform(patch("/v1/permission/update/1")
        .content(body)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(PermissionConstant.UPDATE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @WithMockUser(authorities = "PERMISSION_DELETE")
  void deletePermission() throws Exception {
    mockMvc.perform(delete("/v1/permission/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(PermissionConstant.DELETE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

}
