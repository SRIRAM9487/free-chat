package com.arch.micro_service.auth_server.user.infrastructure.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.arch.micro_service.auth_server.log.CustomLogger;
import com.arch.micro_service.auth_server.testcontainer.AbstractTestContainer;
import com.arch.micro_service.auth_server.user.application.service.UserLoginService;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserLoginRequest;
import com.arch.micro_service.auth_server.user.infrastructure.dto.response.UserLoginResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
public class UserLoginControllerTest extends AbstractTestContainer {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserLoginService userLoginService;

  @MockitoBean
  private CustomLogger customLogger;

  @BeforeEach
  void setup() {
    doNothing().when(customLogger).success(anyString(), anyString(), any(), any());
    doNothing().when(customLogger).failure(anyString(), anyString(), any());
  }

  @Test
  @Transactional
  void login() throws Exception {

    UserLoginRequest req = new UserLoginRequest("admin", "tester1234");

    when(userLoginService.login(req)).thenReturn(new UserLoginResponse(
        "1",
        "admin",
        List.of("ROLE_CREATE", "ROLE_UPDATE"),
        "thi12345667898sisanthi12345667898sesttokhi12345667898sen"));

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
        .andExpect(jsonPath("$.data.token").exists())
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "USER_PASSWORD_RESET")
  void passwordReset() throws Exception {

    doNothing().when(userLoginService).resetPassword(anyString());

    this.mockMvc.perform(patch("/v1/user/login/reset")
        .param("userId", "1")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data").value("User password reseted"))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  void newPassword() throws Exception {

    String email = "admin@gmail.com";
    String token = "thi12345667898sisanthi12345667898sesttokhi12345667898sen";
    String password = "NewPassword123k";

    doNothing().when(userLoginService).newPassword(email, token, password);

    this.mockMvc.perform(patch("/v1/user/login/new")
        .param("email", email)
        .param("token", token)
        .param("password", password)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data").value("Password updated"))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  void loginUserBadCredentials() throws Exception {

    when(userLoginService.login(any(UserLoginRequest.class)))
        .thenThrow(BadCredentialsException.class);

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
        .andExpect(status().is(HttpStatus.FORBIDDEN.value()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

}
