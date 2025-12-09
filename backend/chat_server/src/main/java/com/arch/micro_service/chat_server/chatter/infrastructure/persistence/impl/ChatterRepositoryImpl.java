package com.arch.micro_service.chat_server.chatter.infrastructure.persistence.impl;

import java.util.List;

import com.arch.micro_service.chat_server.chatter.domain.entity.Chatter;
import com.arch.micro_service.chat_server.chatter.domain.exception.ChatterException;
import com.arch.micro_service.chat_server.chatter.infrastructure.persistence.ChatterRepository;
import com.arch.micro_service.chat_server.logger.context.MetaContextHolder;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ChatterRepositoryImpl implements ChatterRepository {

  private final JdbcTemplate jdbcTemplate;

  private final RowMapper<Chatter> mapper = (res, row) -> {
    Chatter chatter = new Chatter();
    chatter.setId(res.getLong("id"));
    chatter.setName(res.getString("name"));
    chatter.setUserId(res.getLong("user_id"));
    chatter
        .setCreatedAt(res.getTimestamp("created_at") != null ? res.getTimestamp("created_at").toLocalDateTime() : null);
    chatter.setCreatedBy(res.getString("created_by"));
    chatter
        .setUpdatedAt(res.getTimestamp("updated_at") != null ? res.getTimestamp("updated_at").toLocalDateTime() : null);
    chatter.setUpdatedBy(res.getString("updated_by"));
    return chatter;
  };

  private final RowMapper<Chatter> deleteMapper = (res, row) -> {
    Chatter chatter = new Chatter();
    chatter.setId(res.getLong("id"));
    chatter.setName(res.getString("name"));
    chatter.setUserId(res.getLong("user_id"));
    chatter
        .setCreatedAt(res.getTimestamp("created_at") != null ? res.getTimestamp("created_at").toLocalDateTime() : null);
    chatter.setCreatedBy(res.getString("created_by"));
    chatter
        .setUpdatedAt(res.getTimestamp("updated_at") != null ? res.getTimestamp("updated_at").toLocalDateTime() : null);
    chatter.setUpdatedBy(res.getString("updated_by"));
    chatter
        .setDeletedAt(res.getTimestamp("deleted_at") != null ? res.getTimestamp("deleted_at").toLocalDateTime() : null);
    chatter.setDeletedBy(res.getString("deleted_by"));
    return chatter;
  };

  @Override
  public List<Chatter> findAll() {
    String sql = """
        SELECT id,name,user_id,created_at,created_by,updated_at,updated_by FROM chatter
        WHERE deleted_at IS NULL
        """;
    return jdbcTemplate.query(sql, mapper);
  }

  @Override
  public Chatter findById(Long id) {
    String sql = """
        SELECT id,name,user_id,created_at,created_by,updated_at,updated_by FROM chatter
        WHERE id =? AND deleted_at IS NULL
        """;
    try {
      return jdbcTemplate.queryForObject(sql, mapper, id);
    } catch (EmptyResultDataAccessException ex) {
      throw ChatterException.notFound();
    }
  }

  @Override
  public Chatter save(Chatter chatGroup) {
    String sql = """
          INSERT INTO chatter(name,user_id,created_by)
          VALUES (?,?,?)
          RETURNING id,name,user_id, created_at, created_by, updated_at, updated_by;
        """;
    try {
      return jdbcTemplate.queryForObject(sql, mapper, chatGroup.getName(), chatGroup.getUserId(),
          chatGroup.getCreatedBy());
    } catch (DataIntegrityViolationException ex) {
      throw ChatterException.uniqueUserId();
    }
  }

  @Override
  public Chatter update(Chatter chatGroup) {
    String sql = """
        UPDATE chatter
        SET
        name =?,
        user_id=?,
        updated_at=NOW(),
        updated_by =?
        WHERE id =? AND deleted_at IS NULL
        RETURNING id,name,user_id, created_at, created_by, updated_at, updated_by;
        """;
    try {
      return jdbcTemplate.queryForObject(sql, mapper, chatGroup.getName(), chatGroup.getUserId(),
          chatGroup.getUpdatedBy(), chatGroup.getId());
    } catch (DataIntegrityViolationException ex) {
      throw ChatterException.uniqueUserId();
    } catch (EmptyResultDataAccessException ex) {
      throw ChatterException.notFound();
    }
  }

  @Override
  public Chatter delete(Long id) {
    String sql = """
        UPDATE chatter
        SET
        deleted_at=NOW(),
        deleted_by =?
        WHERE id =? AND deleted_at IS NULL
        RETURNING id,name,user_id, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by;
        """;

    try {
      return jdbcTemplate.queryForObject(sql, deleteMapper, MetaContextHolder.get().getUserId(), id);
    } catch (EmptyResultDataAccessException ex) {
      throw ChatterException.notFound();
    }
  }

}
