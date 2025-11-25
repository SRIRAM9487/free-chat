package com.arch.micro_service.auth_server.user.infrastructure.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arch.micro_service.auth_server.user.application.constant.UserCrudConstant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
public class UserCrudControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @Transactional
  @WithMockUser(authorities = "USER_VIEW")
  void getAll() throws Exception {
    this.mockMvc.perform(get("/v1/user")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").isArray())
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "USER_VIEW")
  void getById() throws Exception {
    this.mockMvc.perform(get("/v1/user/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data.id").value("1"))
        .andExpect(jsonPath("$.data.userName").value("admin"))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "USER_CREATE")
  void createUser() throws Exception {
    String body = """
            {
            "name": "tester",
            "userName": "tester",
            "password": "test1",
            "email": "tester1@gmail.com",
            "gender": "FEMALE",
            "accountNonExpired": "true",
            "accountNonLocked": "true",
            "enabled": "true",
            "roles": [
                "1",
                "2",
                "3"
            ]
        }
            """;
    this.mockMvc.perform(post("/v1/user/create")
        .content(body)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(UserCrudConstant.CREATE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "USER_UPDATE")
  void updateUser() throws Exception {
    String body = """
            {
            "name": "updatedTester",
            "userName": "test",
            "password": "test1",
            "email": "tester1@gmail.com",
            "gender": "FEMALE",
            "accountNonExpired": "true",
            "accountNonLocked": "true",
            "enabled": "true",
            "roles": [
                "1",
                "2",
                "3"
            ]
        }
            """;
    this.mockMvc.perform(patch("/v1/user/update/1")
        .content(body)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(UserCrudConstant.UPDATE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "USER_DELETE")
  void deleteUser() throws Exception {
    this.mockMvc.perform(delete("/v1/user/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(UserCrudConstant.DELETE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

}
