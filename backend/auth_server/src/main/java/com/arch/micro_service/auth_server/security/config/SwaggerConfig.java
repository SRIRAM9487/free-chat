package com.arch.micro_service.auth_server.security.config;

import java.io.InputStream;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {

  @Bean
  public GroupedOpenApi authApi() {
    return GroupedOpenApi.builder()
        .group("auth-api")
        .addOpenApiCustomizer(openApi -> {
          try (InputStream is = getClass().getClassLoader().getResourceAsStream("openapi/v1_auth.yaml")) {
            String yaml = new String(is.readAllBytes());
            OpenAPI parsed = Yaml.mapper().readValue(yaml, OpenAPI.class);

            openApi.info(parsed.getInfo());
            openApi.setPaths(parsed.getPaths());
            openApi.setComponents(parsed.getComponents());

          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        })
        .pathsToMatch("/auth/**")
        .build();
  }

}
