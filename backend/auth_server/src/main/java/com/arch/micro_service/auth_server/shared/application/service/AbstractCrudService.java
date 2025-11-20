package com.arch.micro_service.auth_server.shared.application.service;

import java.util.List;
import java.util.UUID;

import com.arch.micro_service.auth_server.shared.domain.entity.SoftDelete;
import com.arch.micro_service.auth_server.shared.domain.exception.BaseException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Abstract class for all crud service for entites to reduce
 * code repition
 */
@Service
public abstract class AbstractCrudService<ENTITY extends SoftDelete, EXCEPTION extends BaseException> {

  private final JpaRepository<ENTITY, UUID> repository;

  public AbstractCrudService(JpaRepository<ENTITY, UUID> repository) {
    this.repository = repository;
  }

  /**
   * find All entity
   */
  public List<ENTITY> findAll() {
    List<ENTITY> entities = repository
        .findAll()
        .stream()
        .filter(e -> !e.isDeleted())
        .toList();
    if (entities == null)
      throw notFound();
    return entities;
  };

  /**
   * find by UUID id
   */
  public ENTITY findById(UUID id) {
    ENTITY entity = repository.findById(id).orElseThrow(() -> notFound());
    if (entity.isDeleted())
      throw notFound();
    return entity;
  }

  /**
   * find by String id
   */
  public ENTITY findById(String id) {
    return findById(UUID.fromString(id));
  }

  /**
   * Save the entity
   */
  public ENTITY save(ENTITY entity) {
    return repository.save(entity);
  }

  /**
   * Save all the entity
   */
  @Transactional
  public List<ENTITY> saveAll(List<ENTITY> entities) {
    return repository.saveAll(entities);
  }

  /**
   * Soft delete the entity
   */
  public ENTITY delete(ENTITY entity) {
    entity.softDelete();
    return save(entity);
  }

  /**
   * Soft delete the All entity
   */
  @Transactional
  public List<ENTITY> deleteAll(List<ENTITY> entities) {
    for (ENTITY entity : entities) {
      entity.softDelete();
    }
    return saveAll(entities);
  }

  /**
   * Soft delete the entity
   */
  public ENTITY deleteById(String id) {
    ENTITY entity = findById(id);
    entity.softDelete();
    return save(entity);
  }

  /**
   * Soft delete the entity
   */
  public long count() {
    return repository.count();
  }

  /**
   * Not found execption thrown
   */
  public abstract EXCEPTION notFound();

}
