package com.arch.micro_service.auth_server.user.application.service.impl;

import java.time.Duration;

import com.arch.micro_service.auth_server.user.application.service.CacheService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheServiceImpl implements CacheService {

  private final RedisTemplate<String, String> redisTemplate;

  public RedisCacheServiceImpl(@Qualifier("string") RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public String retrive(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  @Override
  public void save(String key, String value) {
    redisTemplate.opsForValue().set(key, value, Duration.ofMinutes(10));
  }

  @Override
  public void save(String key, String value, int ttl) {
    redisTemplate.opsForValue().set(key, value, Duration.ofMinutes(ttl));
  }

  @Override
  public void remove(String key) {
    redisTemplate.delete(key);
  }

}
