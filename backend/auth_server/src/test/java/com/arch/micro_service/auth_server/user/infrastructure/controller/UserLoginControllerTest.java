package com.arch.micro_service.auth_server.user.infrastructure.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

import com.arch.micro_service.auth_server.security.web.filter.JwtFilter;
import com.arch.micro_service.auth_server.user.application.constant.UserCrudConstant;
import com.arch.micro_service.auth_server.user.application.service.UserLoginService;
import com.arch.micro_service.auth_server.user.application.service.impl.UserLoginServiceImpl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserLoginController.class)
public class UserLoginControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
  }

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
