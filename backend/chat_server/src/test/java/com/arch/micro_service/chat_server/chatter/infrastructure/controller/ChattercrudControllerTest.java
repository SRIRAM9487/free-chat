package com.arch.micro_service.chat_server.chatter.infrastructure.controller;

import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arch.micro_service.chat_server.chatgroup.application.constant.ChatGroupConstant;
import com.arch.micro_service.chat_server.chatgroup.domain.exception.type.ChatGroupExceptionType;
import com.arch.micro_service.chat_server.chatter.application.constant.ChatterConstant;
import com.arch.micro_service.chat_server.chatter.domain.exception.type.ChatterExceptionType;
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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureMockMvc
public class ChattercrudControllerTest extends AbstractTestContainer {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_READ")
  void getAll() throws Exception {
    this.mockMvc.perform(
        get("/v1/chatter")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").isArray())
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_READ")
  void getById() throws Exception {
    this.mockMvc.perform(
        get("/v1/chatter/1")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data.id").value(1L))
        .andExpect(jsonPath("$.data.name").value("Alice"))
        .andExpect(jsonPath("$.data.userId").value(1L))
        .andExpect(jsonPath("$.data.createdAt").exists())
        .andExpect(jsonPath("$.data.createdBy").exists())
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_READ")
  void getById_notFound_Exception() throws Exception {
    this.mockMvc.perform(
        get("/v1/chatter/1999")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.GET.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/chatter/1999"))
        .andExpect(jsonPath("$.message").value(ChatterExceptionType.CHATTER_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(ChatterExceptionType.CHATTER_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_CREATE")
  void create() throws Exception {
    String body = """
        {
           "name": "chatter",
           "userId": "199"
        }

                """;
    this.mockMvc.perform(
        post("/v1/chatter/create")
            .accept(MediaType.APPLICATION_JSON)
            .content(body)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(ChatterConstant.CREATE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_CREATE")
  void create_uniqueUserId_Exception() throws Exception {
    String body = """
        {
           "name": "chatter",
           "userId": "1"
        }

                """;
    this.mockMvc.perform(
        post("/v1/chatter/create")
            .accept(MediaType.APPLICATION_JSON)
            .content(body)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.POST.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.CONFLICT.name()))
        .andExpect(jsonPath("$.path").value("/v1/chatter/create"))
        .andExpect(jsonPath("$.message").value(ChatterExceptionType.UNIQUE_USER_ID.getMessage()))
        .andExpect(jsonPath("$.code").value(ChatterExceptionType.UNIQUE_USER_ID.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_UPDATE")
  void update() throws Exception {
    String body = """
        {
           "name": "chatter",
           "userId": "199"
        }

                """;
    this.mockMvc.perform(
        patch("/v1/chatter/update/1")
            .accept(MediaType.APPLICATION_JSON)
            .content(body)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(ChatterConstant.UPDATE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_UPDATE")
  void update_notFound_Exception() throws Exception {
    String body = """
        {
           "name": "chatter",
           "userId": "1"
        }

                """;
    this.mockMvc.perform(
        patch("/v1/chatter/update/8888")
            .accept(MediaType.APPLICATION_JSON)
            .content(body)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.PATCH.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/chatter/update/8888"))
        .andExpect(jsonPath("$.message").value(ChatterExceptionType.CHATTER_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(ChatterExceptionType.CHATTER_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_UPDATE")
  void update_uniqueUserId_Exception() throws Exception {
    String body = """
        {
           "name": "chatter",
           "userId": "1"
        }

                """;
    this.mockMvc.perform(
        patch("/v1/chatter/update/3")
            .accept(MediaType.APPLICATION_JSON)
            .content(body)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.PATCH.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.CONFLICT.name()))
        .andExpect(jsonPath("$.path").value("/v1/chatter/update/3"))
        .andExpect(jsonPath("$.message").value(ChatterExceptionType.UNIQUE_USER_ID.getMessage()))
        .andExpect(jsonPath("$.code").value(ChatterExceptionType.UNIQUE_USER_ID.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_DELETE")
  void deleteById() throws Exception {
    this.mockMvc.perform(
        delete("/v1/chatter/1")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(ChatterConstant.DELETE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHATTER_DELETE")
  void deleteById_notFound_Exception() throws Exception {
    this.mockMvc.perform(
        delete("/v1/chatter/999")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.DELETE.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/chatter/999"))
        .andExpect(jsonPath("$.message").value(ChatterExceptionType.CHATTER_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(ChatterExceptionType.CHATTER_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }
}
