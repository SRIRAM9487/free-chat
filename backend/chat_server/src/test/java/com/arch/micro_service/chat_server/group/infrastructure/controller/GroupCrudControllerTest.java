package com.arch.micro_service.chat_server.group.infrastructure.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arch.micro_service.chat_server.group.application.constant.GroupConstant;
import com.arch.micro_service.chat_server.group.domain.exception.type.GroupExceptionType;
import com.arch.micro_service.chat_server.logger.CustomLogger;
import com.arch.micro_service.chat_server.testcontainers.AbstractTestContainer;

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
public class GroupCrudControllerTest extends AbstractTestContainer {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private CustomLogger customLogger;

  @BeforeEach
  void setup() {
    doNothing().when(customLogger).success(anyString(), any(), any());
    doNothing().when(customLogger).failure(anyString(), any());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_READ")
  void getAll() throws Exception {
    this.mockMvc
        .perform(get("/v1/group")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").isArray())
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_READ")
  void get_success() throws Exception {
    this.mockMvc
        .perform(get("/v1/group/1")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data.id").value("1"))
        .andExpect(jsonPath("$.data.name").value("GROUP_1"))
        .andExpect(jsonPath("$.data.description").value("THIS IS AN TEST GROUP 1"))
        .andExpect(jsonPath("$.data.groupMembers").exists())
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_READ")
  void get_notFound() throws Exception {
    this.mockMvc
        .perform(get("/v1/group/99999")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.GET.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/group/99999"))
        .andExpect(jsonPath("$.message").value(GroupExceptionType.GROUP_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(GroupExceptionType.GROUP_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_CREATE")
  void create_success() throws Exception {

    String req = """
                {
          "name": "TEST_GROUP_12",
          "description": "A group for backend devs",
          "groupMemberIds": [
            {
              "chatterId": 1,
              "accessLevel": "ADMIN"
            },
            {
              "chatterId": 2,
              "accessLevel": "MEMBER"
            }
          ],
          "adminOnly": false
        }
                    """;

    this.mockMvc
        .perform(post("/v1/group/create")
            .content(req)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(GroupConstant.CREATE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_UPDATE")
  void update_success() throws Exception {

    String req = """
                {
          "name": "TEST_GROUP_12",
          "description": "A group for backend devs",
          "groupMemberIds": [
            {
              "chatterId": 1,
              "accessLevel": "ADMIN"
            },
            {
              "chatterId": 2,
              "accessLevel": "MEMBER"
            }
          ],
          "adminOnly": false
        }
                    """;

    this.mockMvc
        .perform(patch("/v1/group/update/1")
            .content(req)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(GroupConstant.UPDATE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_UPDATE")
  void update_groupNotFound() throws Exception {

    String req = """
                {
          "name": "TEST_GROUP_12",
          "description": "A group for backend devs",
          "groupMemberIds": [
            {
              "chatterId": 1,
              "accessLevel": "ADMIN"
            },
            {
              "chatterId": 2,
              "accessLevel": "MEMBER"
            }
          ],
          "adminOnly": false
        }
                    """;

    this.mockMvc
        .perform(patch("/v1/group/update/9999")
            .content(req)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.PATCH.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/group/update/9999"))
        .andExpect(jsonPath("$.message").value(GroupExceptionType.GROUP_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(GroupExceptionType.GROUP_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_DELETE")
  void delete_success() throws Exception {
    this.mockMvc
        .perform(delete("/v1/group/1")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(GroupConstant.DELETE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_READ")
  void delete_notFound() throws Exception {
    this.mockMvc
        .perform(delete("/v1/group/999999")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.DELETE.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/group/999999"))
        .andExpect(jsonPath("$.message").value(GroupExceptionType.GROUP_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(GroupExceptionType.GROUP_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }
}
