package com.arch.micro_service.auth_server.user.infrastructure.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.arch.micro_service.auth_server.log.CustomLogger;
import com.arch.micro_service.auth_server.shared.domain.constant.Gender;
import com.arch.micro_service.auth_server.testcontainer.AbstractTestContainer;
import com.arch.micro_service.auth_server.user.application.constant.UserCrudConstant;
import com.arch.micro_service.auth_server.user.application.service.UserCrudService;
import com.arch.micro_service.auth_server.user.domain.entity.User;
import com.arch.micro_service.auth_server.user.domain.vo.Email;
import com.arch.micro_service.auth_server.user.domain.vo.Password;
import com.arch.micro_service.auth_server.user.infrastructure.dto.request.UserCreateRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
public class UserCrudControllerTest extends AbstractTestContainer {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserCrudService userCrudService;

  @MockitoBean
  private CustomLogger customLogger;

  private List<User> users;

  @BeforeEach
  void setup() {
    users = new ArrayList<>();
    for (int i = 1; i < 10; i++) {

      String name = "test" + i;
      User user = User
          .builder()
          .userName(name)
          .password(Password.create(name))
          .email(Email.create(name + "@gmail.com"))
          .gender(Gender.MALE)
          .accountNonLocked(true)
          .accountNonExpired(true)
          .enabled(true)
          .build();
      user.setName(name);
      user.setId((long) i);

      users.add(user);
    }
    doNothing().when(customLogger).success(anyString(), anyString(), any(), any());
    doNothing().when(customLogger).failure(anyString(), anyString(), any());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "USER_READ")
  void getAll() throws Exception {

    when(userCrudService.getAll()).thenReturn(users);

    this.mockMvc.perform(get("/v1/user")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").isArray())
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "USER_READ")
  void getById() throws Exception {
    when(userCrudService.get("1")).thenReturn(users.getFirst());
    this.mockMvc.perform(get("/v1/user/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data.id").value("1"))
        .andExpect(jsonPath("$.data.userName").value("test1"))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "USER_CREATE")
  void createUser() throws Exception {
    String body = """
            {
            "name": "test1",
            "userName": "test1",
            "password": "test1",
            "email": "tester1@gmail.com",
            "gender": "MALE",
            "accountNonExpired": "true",
            "accountNonLocked": "true",
            "enabled": "true",
            "roles": []
        }
            """;

    when(userCrudService.create(any(UserCreateRequest.class))).thenReturn(users.getFirst());
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
            "roles": []
        }
            """;
    when(userCrudService.update(eq("1"), any(UserCreateRequest.class))).thenReturn(users.getFirst());
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
    when(userCrudService.delete("1")).thenReturn(users.getFirst());
    this.mockMvc.perform(delete("/v1/user/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(UserCrudConstant.DELETE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

}
