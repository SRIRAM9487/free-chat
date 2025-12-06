package com.arch.micro_service.chat_server.chatter.infrastructure.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arch.micro_service.chat_server.chatter.application.constant.ChatterConstant;
import com.arch.micro_service.chat_server.chatter.domain.exception.type.ChatterExceptionType;
import com.arch.micro_service.chat_server.shared.domain.exception.CustomDataIntegrityExceptionType;
import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
public class ChatterCrudControllerTest extends AbstractTestContainer {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_READ")
  void getAll() throws Exception {
    this.mockMvc
        .perform(get("/v1/chatter")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").isArray())
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_READ")
  void get_success() throws Exception {
    this.mockMvc
        .perform(get("/v1/chatter/1")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data.id").value("1"))
        .andExpect(jsonPath("$.data.userId").value("1"))
        .andExpect(jsonPath("$.data.name").exists())
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_READ")
  void get_notFound() throws Exception {
    this.mockMvc
        .perform(get("/v1/chatter/99999")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.GET.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/chatter/99999"))
        .andExpect(jsonPath("$.message").value(ChatterExceptionType.CHATTER_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(ChatterExceptionType.CHATTER_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_CREATE")
  void create_success() throws Exception {

    String req = """
        {
            "name": "CHATTER_TESTER_1000",
            "userId": 111
          }
            """;

    this.mockMvc
        .perform(post("/v1/chatter/create")
            .content(req)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(ChatterConstant.CREATE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_CREATE")
  void create_conflict() throws Exception {

    String req = """
        {
            "name": "CHATTER_TESTER_1000",
            "userId": 1
          }
            """;

    this.mockMvc
        .perform(post("/v1/chatter/create")
            .content(req)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.POST.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.CONFLICT.name()))
        .andExpect(jsonPath("$.path").value("/v1/chatter/create"))
        .andExpect(jsonPath("$.message").value(CustomDataIntegrityExceptionType.CHATTER_UNIQUE_USER_ID.getMessage()))
        .andExpect(jsonPath("$.code").value(CustomDataIntegrityExceptionType.CHATTER_UNIQUE_USER_ID.name()))
        .andExpect(jsonPath("$.timeStamp").exists());

  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_UPDATE")
  void update_success() throws Exception {

    String req = """
        {
            "name": "CHATTER_TESTER_1000",
            "userId": 111
          }
            """;

    this.mockMvc
        .perform(patch("/v1/chatter/update/1")
            .content(req)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(ChatterConstant.UPDATE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_UPDATE")
  void update_chatterNotFound() throws Exception {

    String req = """
        {
            "name": "CHATTER_TESTER_1000",
            "userId": 111
          }
            """;

    this.mockMvc
        .perform(patch("/v1/chatter/update/9999")
            .content(req)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.PATCH.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/chatter/update/9999"))
        .andExpect(jsonPath("$.message").value(ChatterExceptionType.CHATTER_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(ChatterExceptionType.CHATTER_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  // @Test
  // @Transactional
  // @WithMockUser(authorities = "CHATTER_UPDATE")
  void update_conflict() throws Exception {

    String req = """
        {
            "name": "CHATTER_TESTER_1000",
            "userId": 1
          }
            """;

    this.mockMvc
        .perform(patch("/v1/chatter/update/9")
            .content(req)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.GET.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.CONFLICT.name()))
        .andExpect(jsonPath("$.path").value("/v1/chatter/update/1"))
        .andExpect(jsonPath("$.message").value(CustomDataIntegrityExceptionType.CHATTER_UNIQUE_USER_ID.getMessage()))
        .andExpect(jsonPath("$.code").value(CustomDataIntegrityExceptionType.CHATTER_UNIQUE_USER_ID.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_DELETE")
  void delete_success() throws Exception {
    this.mockMvc
        .perform(delete("/v1/chatter/1")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(ChatterConstant.DELETE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_READ")
  void delete_notFound() throws Exception {
    this.mockMvc
        .perform(delete("/v1/chatter/999999")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.DELETE.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/chatter/999999"))
        .andExpect(jsonPath("$.message").value(ChatterExceptionType.CHATTER_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(ChatterExceptionType.CHATTER_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }
}
