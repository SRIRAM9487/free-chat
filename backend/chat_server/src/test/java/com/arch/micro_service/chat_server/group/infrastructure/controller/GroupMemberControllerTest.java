package com.arch.micro_service.chat_server.group.infrastructure.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.arch.micro_service.chat_server.chatgroup.application.constant.GroupMemberConstant;
import com.arch.micro_service.chat_server.chatgroup.domain.entity.AccessLevel;
import com.arch.micro_service.chat_server.chatgroup.domain.exception.type.GroupMemberExceptionType;
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
public class GroupMemberControllerTest extends AbstractTestContainer {

  @Autowired
  private MockMvc mvc;

  private final Long notFound = 9999L;

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_MEMBER_READ")
  void getById() throws Exception {
    this.mvc.perform(get("/v1/group-member/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data.id").value(1L))
        .andExpect(jsonPath("$.data.chatterId").value(1L))
        .andExpect(jsonPath("$.data.groupId").value(1L))
        .andExpect(jsonPath("$.data.accessLevel").value(AccessLevel.CREATOR.name()))
        .andExpect(jsonPath("$.data.restricted").value(false))
        .andExpect(jsonPath("$.data.createdBy").exists())
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_MEMBER_READ")
  void getById_notFound_Exception() throws Exception {

    this.mvc.perform(get("/v1/group-member/" + notFound)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.GET.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/group-member/" + notFound))
        .andExpect(jsonPath("$.message").value(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_MEMBER_READ")
  void getByGroupId() throws Exception {
    this.mvc.perform(get("/v1/group-member/group/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").exists())
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_MEMBER_READ")
  void getByGroupId_notFound_Exception() throws Exception {

    this.mvc.perform(get("/v1/group-member/group/" + notFound)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.GET.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/group-member/group/" + notFound))
        .andExpect(jsonPath("$.message").value(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_MEMBER_CREATE")
  void create() throws Exception {

    String body = """
            {
          "chatterId":1,
          "groupId":1,
          "accessLevel":"CREATOR",
          "restricted": false
        }

            """;
    this.mvc.perform(post("/v1/group-member/create")
        .content(body)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(GroupMemberConstant.CREATE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_MEMBER_CREATE")
  void create_invalidChatterId() throws Exception {

    String body = """
            {
          "chatterId":9999,
          "groupId":1,
          "accessLevel":"CREATOR",
          "restricted": false
        }

            """;
    this.mvc.perform(post("/v1/group-member/create")
        .content(body)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.POST.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/group-member/create"))
        .andExpect(jsonPath("$.message").value(GroupMemberExceptionType.CHATTER_ID_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(GroupMemberExceptionType.CHATTER_ID_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_MEMBER_CREATE")
  void create_invalidGroupId() throws Exception {

    String body = """
            {
          "chatterId":1,
          "groupId":9999,
          "accessLevel":"CREATOR",
          "restricted": false
        }

            """;
    this.mvc.perform(post("/v1/group-member/create")
        .content(body)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.POST.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/group-member/create"))
        .andExpect(jsonPath("$.message").value(GroupMemberExceptionType.GROUP_ID_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(GroupMemberExceptionType.GROUP_ID_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_MEMBER_EDIT")
  void restrict() throws Exception {

    this.mvc.perform(put("/v1/group-member/restrict/1")
        .param("restrict", "true")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(GroupMemberConstant.RESTRICTED))
        .andExpect(jsonPath("$.timeStamp").exists());

  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_MEMBER_EDIT")
  void unrestrict() throws Exception {

    this.mvc.perform(put("/v1/group-member/restrict/1")
        .param("restrict", "false")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(GroupMemberConstant.UNRESTRICTED))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_MEMBER_EDIT")
  void restrict_notFound_Exception() throws Exception {

    this.mvc.perform(put("/v1/group-member/restrict/" + notFound)
        .accept(MediaType.APPLICATION_JSON)
        .param("restrict", "true")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.PUT.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/group-member/restrict/" + notFound))
        .andExpect(jsonPath("$.message").value(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_MEMBER_DELETE")
  void deleteById() throws Exception {
    this.mvc.perform(delete("/v1/group-member/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(GroupMemberConstant.DELETE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_MEMBER_DELETE")
  void deleteById_notFound_Exception() throws Exception {

    this.mvc.perform(delete("/v1/group-member/" + notFound)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.DELETE.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/group-member/" + notFound))
        .andExpect(jsonPath("$.message").value(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_MEMBER_DELETE")
  void deleteByGroupId() throws Exception {
    this.mvc.perform(delete("/v1/group-member/group/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(GroupMemberConstant.DELETE))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

  @Test
  @Transactional
  @WithMockUser(authorities = "GROUP_MEMBER_DELETE")
  void deleteByGroupId_notFound_Exception() throws Exception {

    this.mvc.perform(delete("/v1/group-member/group/" + notFound)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.method").value(HttpMethod.DELETE.name()))
        .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.name()))
        .andExpect(jsonPath("$.path").value("/v1/group-member/group/" + notFound))
        .andExpect(jsonPath("$.message").value(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.getMessage()))
        .andExpect(jsonPath("$.code").value(GroupMemberExceptionType.GROUP_MEMEBER_NOT_FOUND.name()))
        .andExpect(jsonPath("$.timeStamp").exists());
  }

}
