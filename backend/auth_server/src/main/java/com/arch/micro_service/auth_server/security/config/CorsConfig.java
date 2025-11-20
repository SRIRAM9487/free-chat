package com.arch.micro_service.auth_server.security.config;

import com.arch.micro_service.auth_server.security.constant.CorsConstant;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins(CorsConstant.ALLOWED_ORIGINS)
        .allowedMethods(CorsConstant.ALLOWED_METHODS)
        .allowedHeaders(CorsConstant.ALLOWED_HEADERS)
        .allowCredentials(true);
  }

}
