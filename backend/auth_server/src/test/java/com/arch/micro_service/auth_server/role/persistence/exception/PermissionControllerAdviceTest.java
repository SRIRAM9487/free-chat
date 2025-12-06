package com.arch.micro_service.auth_server.role.persistence.exception;

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
import com.arch.micro_service.auth_server.role.domain.exception.type.PermissionExceptionType;
import com.arch.micro_service.auth_server.testcontainer.AbstractTestContainer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
public class PermissionControllerAdviceTest extends AbstractTestContainer {

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
  @WithMockUser(authorities = "PERMISSION_READ")
  @Transactional
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
  @Transactional
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
        .andExpect(status().is(HttpStatus.CONFLICT.value()))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.path").value("/v1/permission/create"))
        .andExpect(jsonPath("$.message").value(PermissionExceptionType.UNIQUE_TITLE.getMessage()))
        .andExpect(jsonPath("$.code").value(PermissionExceptionType.UNIQUE_TITLE.name()));
  }

  @Test
  @WithMockUser(authorities = "PERMISSION_UPDATE")
  @Transactional
  void updatePermissionNotFound() throws Exception {

    String body = """
        {
        "title" : "ROLE_CREATE",
        "active" : true
        }
        """;

    mockMvc.perform(patch("/v1/permission/update/9999")
        .content(body)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.PATCH.name()))
        .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.path").value("/v1/permission/update/9999"))
        .andExpect(jsonPath("$.message").value(PermissionExceptionType.PERMISSION_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(PermissionExceptionType.PERMISSION_NOT_FOUND.name()));
  }

  @Test
  @WithMockUser(authorities = "PERMISSION_DELETE")
  @Transactional
  void deletePermissionNotFound() throws Exception {

    mockMvc.perform(delete("/v1/permission/9999")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.DELETE.name()))
        .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
        .andExpect(jsonPath("$.timeStamp").exists())
        .andExpect(jsonPath("$.path").value("/v1/permission/9999"))
        .andExpect(jsonPath("$.message").value(PermissionExceptionType.PERMISSION_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(PermissionExceptionType.PERMISSION_NOT_FOUND.name()));
  }

}
