package com.arch.micro_service.auth_server.role.persistence.exception;

import com.arch.micro_service.auth_server.role.application.constant.PermissionConstant;
import com.arch.micro_service.auth_server.role.domain.exception.type.PermissionExceptionType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
public class PermissionControllerAdviceTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(authorities = "PERMISSION_VIEW")
  void getByIdNotFound() throws Exception {
    mockMvc.perform(get("/v1/permission/9999")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.GET.name()))
        .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.path").value("/v1/permission/9999"))
        .andExpect(jsonPath("$.message").value(PermissionExceptionType.PERMISSION_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(PermissionExceptionType.PERMISSION_NOT_FOUND.name()));
  }

  @Test
  @WithMockUser(authorities = "PERMISSION_CREATE")
  void createPermission() throws Exception {

    String body = """
        {
        "title" : "ROLE_CREATE",
        "active" : true
        }
        """;

    mockMvc.perform(post("/v1/permission/create")
        .content(body)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.POST.name()))
        .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.path").value("/v1/permission/9999"))
        .andExpect(jsonPath("$.message").value(PermissionExceptionType.PERMISSION_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(PermissionExceptionType.PERMISSION_NOT_FOUND.name()));
  }
}
