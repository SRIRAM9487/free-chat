package com.arch.micro_service.chat_server.group.infrastructure.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arch.micro_service.chat_server.chatgroup.application.constant.ChatGroupConstant;
import com.arch.micro_service.chat_server.chatgroup.domain.exception.type.ChatGroupExceptionType;
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
public class ChatGroupCrudControllerTest extends AbstractTestContainer {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @Transactional
  @WithMockUser(authorities = "CHAT_GROUP_READ")
  void getAll() throws Exception {
    this.mockMvc.perform(
        get("/v1/chat-group")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").isArray())
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHAT_GROUP_READ")
  void getById() throws Exception {
    this.mockMvc.perform(get("/v1/chat-group/1")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data.id").value(1L))
        .andExpect(jsonPath("$.data.name").value("Group_Alpha"))
        .andExpect(jsonPath("$.data.description").exists())
        .andExpect(jsonPath("$.data.createdAt").exists())
        .andExpect(jsonPath("$.data.createdBy").exists())
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHAT_GROUP_CREATE")
  void create() throws Exception {
    String body = """
        {
          "name": "test 1",
          "description": "test description 1"
        }
        """;
    this.mockMvc.perform(post("/v1/chat-group/create")
        .content(body)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(ChatGroupConstant.CREATE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHAT_GROUP_UPDATE")
  void update() throws Exception {
    String body = """
        {
          "name": "test 1",
          "description": "test description 1"
        }
        """;
    this.mockMvc.perform(patch("/v1/chat-group/update/1")
        .content(body)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(ChatGroupConstant.UPDATE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHAT_GROUP_UPDATE")
  void update_notFound() throws Exception {
    String body = """
        {
          "name": "test 1",
          "description": "test description 1"
        }
        """;
    this.mockMvc.perform(patch("/v1/chat-group/update/999")
        .content(body)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.PATCH.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/chat-group/update/999"))
        .andExpect(jsonPath("$.message").value(ChatGroupExceptionType.GROUP_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(ChatGroupExceptionType.GROUP_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHAT_GROUP_DELETE")
  void deleteById() throws Exception {
    this.mockMvc.perform(delete("/v1/chat-group/1")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(ChatGroupConstant.DELETE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "CHAT_GROUP_DELETE")
  void delete_notFound() throws Exception {
    this.mockMvc.perform(delete("/v1/chat-group/999")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.DELETE.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/chat-group/999"))
        .andExpect(jsonPath("$.message").value(ChatGroupExceptionType.GROUP_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(ChatGroupExceptionType.GROUP_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

}
