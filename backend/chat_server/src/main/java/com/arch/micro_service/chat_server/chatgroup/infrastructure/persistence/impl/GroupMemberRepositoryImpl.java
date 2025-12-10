package com.arch.micro_service.chat_server.chatgroup.infrastructure.persistence.impl;

import java.util.List;

import com.arch.micro_service.chat_server.chatgroup.domain.entity.AccessLevel;
import com.arch.micro_service.chat_server.chatgroup.domain.entity.GroupMember;
import com.arch.micro_service.chat_server.chatgroup.domain.exception.GroupMemberException;
import com.arch.micro_service.chat_server.chatgroup.infrastructure.persistence.GroupMemberRepository;
import com.arch.micro_service.chat_server.logger.context.MetaContextHolder;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GroupMemberRepositoryImpl implements GroupMemberRepository {

  private final JdbcTemplate jdbcTemplate;

  private final RowMapper<GroupMember> mapper = (res, row) -> {

    GroupMember groupMember = new GroupMember();
    groupMember.setId(res.getLong("id"));
    groupMember.setChatterId(res.getLong("chatter_id"));
    groupMember.setGroupId(res.getLong("group_id"));
    groupMember.setAccessLevel(AccessLevel.valueOf(res.getString("access_level")));
    groupMember.setRestricted(res.getBoolean("restricted"));
    groupMember.setCreatedAt(
        res.getTimestamp("created_at") != null ? res.getTimestamp("created_at").toLocalDateTime()
            : null);
    groupMember.setCreatedBy(res.getString("created_by"));
    groupMember.setUpdatedAt(
        res.getTimestamp("updated_at") != null ? res.getTimestamp("updated_at").toLocalDateTime()
            : null);
    groupMember.setUpdatedBy(res.getString("updated_by"));
    return groupMember;
  };

  @Override
  public GroupMember findById(Long id) {
    String sql = """
            SELECT id ,chatter_id,group_id,access_level,restricted,created_at,created_by,updated_at,updated_by
            FROM group_member WHERE id =? AND deleted_at IS NULL;
        """;
    try {
      return jdbcTemplate.queryForObject(sql, mapper, id);
    } catch (EmptyResultDataAccessException e) {
      throw GroupMemberException.notFound();
    }
  }

  @Override
  public GroupMember save(GroupMember groupMember) {
    String sql = """
            INSERT INTO group_member(chatter_id, group_id, access_level, restricted, created_by)
            VALUES(?,?,?,?,?)
            RETURNING id,chatter_id,group_id,access_level,restricted,created_by,created_at,updated_at,updated_by
        """;

    try {
      return jdbcTemplate.queryForObject(sql, mapper, groupMember.getChatterId(), groupMember.getGroupId(),
          groupMember.getAccessLevel().name(), groupMember.isRestricted(), MetaContextHolder.get().getUserId());
    } catch (DataIntegrityViolationException ex) {
      if (ex.getMessage().contains("Key (chatter_id)")) {
        throw GroupMemberException.chatterIdNotFound();
      } else if (ex.getMessage().contains("Key (group_id)")) {
        throw GroupMemberException.groupIdNotFound();
      } else {
        throw GroupMemberException.notFound();
      }
    }
  }

  @Override
  public GroupMember delete(Long id) {
    String sql = """
        UPDATE group_member
        SET
        deleted_at = NOW(),
        deleted_by = ?
        WHERE id =? AND deleted_at IS NULL
            RETURNING id,chatter_id,group_id,access_level,restricted,created_by,created_at,updated_at,updated_by,deleted_at,deleted_by;
            """;

    try {
      return jdbcTemplate.queryForObject(sql, (res, row) -> {
        GroupMember groupMember = new GroupMember();
        groupMember.setId(res.getLong("id"));
        groupMember.setChatterId(res.getLong("chatter_id"));
        groupMember.setGroupId(res.getLong("group_id"));
        groupMember.setAccessLevel(AccessLevel.valueOf(res.getString("access_level")));
        groupMember.setRestricted(res.getBoolean("restricted"));
        groupMember.setCreatedAt(
            res.getTimestamp("created_at") != null ? res.getTimestamp("created_at").toLocalDateTime()
                : null);
        groupMember.setCreatedBy(res.getString("created_by"));
        groupMember.setUpdatedAt(
            res.getTimestamp("updated_at") != null ? res.getTimestamp("updated_at").toLocalDateTime()
                : null);
        groupMember.setUpdatedBy(res.getString("updated_by"));
        groupMember.setDeletedAt(
            res.getTimestamp("deleted_at") != null ? res.getTimestamp("deleted_at").toLocalDateTime()
                : null);
        groupMember.setDeletedBy(res.getString("deleted_by"));
        return groupMember;
      }, MetaContextHolder.get().getUserId(), id);
    } catch (EmptyResultDataAccessException ex) {
      throw GroupMemberException.notFound();
    }
  }

  @Override
  public GroupMember restrict(Boolean restrict, Long id) {
    String sql = """
        UPDATE group_member
        SET
        restricted=?,
        updated_at = NOW(),
        updated_by = ?
        WHERE id =? AND deleted_at IS NULL
            RETURNING id,chatter_id,group_id,access_level,restricted,created_by,created_at,updated_at,updated_by;
        """;
    try {
      return jdbcTemplate.queryForObject(sql, mapper, restrict, MetaContextHolder.get().getUserId(), id);
    } catch (EmptyResultDataAccessException ex) {
      throw GroupMemberException.notFound();
    }

  }

  @Override
  public GroupMember access(AccessLevel level, Long id) {
    String sql = """
        UPDATE group_member
        SET
        access_level=?,
        updated_at = NOW(),
        updated_by = ?
        WHERE id =? AND deleted_at IS NULL
            RETURNING id,chatter_id,group_id,access_level,restricted,created_by,created_at,updated_at,updated_by;
        """;
    try {
      return jdbcTemplate.queryForObject(sql, mapper, level.name(), MetaContextHolder.get().getUserId(), id);
    } catch (EmptyResultDataAccessException ex) {
      throw GroupMemberException.notFound();
    }
  }

  @Override
  public List<GroupMember> findByGroupId(Long id) {
    String sql = """
            SELECT id ,chatter_id,group_id,access_level,restricted,created_at,created_by,updated_at,updated_by
            FROM group_member WHERE group_id =? AND deleted_at IS NULL;
        """;
    List<GroupMember> members = jdbcTemplate.query(sql, mapper, id);
    if (members.isEmpty()) {
      throw GroupMemberException.notFound();
    }
    return members;
  }

  @Override
  public List<GroupMember> deleteByGroupId(Long id) {
    String sql = """
        UPDATE group_member
        SET
        deleted_at = NOW(),
        deleted_by = ?
        WHERE group_id =? AND deleted_at IS NULL
            RETURNING id,chatter_id,group_id,access_level,restricted,created_by,created_at,updated_at,updated_by,deleted_by,deleted_at;
        """;
    List<GroupMember> members = jdbcTemplate.query(sql, mapper, MetaContextHolder.get().getUserId(), id);
    if (members.isEmpty()) {
      throw GroupMemberException.notFound();
    }
    return members;
  }

}
