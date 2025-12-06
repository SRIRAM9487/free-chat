package com.arch.micro_service.auth_server.user.infrastructure.exception;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arch.micro_service.auth_server.log.CustomLogger;
import com.arch.micro_service.auth_server.testcontainer.AbstractTestContainer;
import com.arch.micro_service.auth_server.user.domain.exception.type.UserExceptionType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
public class UserCrudControllerAdvice extends AbstractTestContainer {

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
  @Transactional
  @WithMockUser(authorities = "USER_READ")
  void getByIdNotFound() throws Exception {
    this.mockMvc.perform(get("/v1/user/9999")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.GET.name()))
        .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.path").value("/v1/user/9999"))
        .andExpect(jsonPath("$.message").value(UserExceptionType.USER_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(UserExceptionType.USER_NOT_FOUND.name()));
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "USER_CREATE")
  void createUserUserNameUniqueKeyViolation() throws Exception {
    String body = """
            {
            "name": "tester",
            "userName": "admin",
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
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.POST.name()))
        .andExpect(status().is(HttpStatus.CONFLICT.value()))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.path").value("/v1/user/create"))
        .andExpect(jsonPath("$.message").value(UserExceptionType.USER_NAME_MUST_BE_UNIQUE.getMessage()))
        .andExpect(jsonPath("$.code").value(UserExceptionType.USER_NAME_MUST_BE_UNIQUE.name()));
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "USER_CREATE")
  void createUserUniqueEmail() throws Exception {

    String body = """
            {
            "name": "tester",
            "userName": "tester",
            "password": "test1",
            "email": "admin@company.com",
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
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.POST.name()))
        .andExpect(status().is(HttpStatus.CONFLICT.value()))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.path").value("/v1/user/create"))
        .andExpect(jsonPath("$.message").value(UserExceptionType.USER_EMAIL_MUST_BE_UNIQUE.getMessage()))
        .andExpect(jsonPath("$.code").value(UserExceptionType.USER_EMAIL_MUST_BE_UNIQUE.name()));
  }

}
