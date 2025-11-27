package com.arch.micro_service.auth_server.user.application.service;

public interface CacheService {

  String retrive(String key);

  void save(String key, String value);

  void save(String key, String value, int ttl);

  void remove(String key);

}
