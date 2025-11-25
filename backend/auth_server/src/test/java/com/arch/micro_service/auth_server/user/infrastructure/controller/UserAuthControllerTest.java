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
public class UserAuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @Transactional
  void login() throws Exception {
    String body = """
            {
            "userId": "admin",
            "password": "tester1234"
        }
            """;
    this.mockMvc.perform(post("/v1/user/login")
        .content(body)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.userId").value("1"))
        .andExpect(jsonPath("$.data.userName").value("admin"))
        .andExpect(jsonPath("$.data.role").isArray())
        .andExpect(jsonPath("$.timeStamp").exists());
  }
}
