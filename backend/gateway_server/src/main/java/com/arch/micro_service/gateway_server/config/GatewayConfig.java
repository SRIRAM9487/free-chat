package com.arch.micro_service.gateway_server.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder builder) {

    return builder
        .routes()
        .route("Auth Service", r -> {
          return r
              .path("/auth/**")
              .filters(f -> f.stripPrefix(1))
              .uri("lb://AUTH-SERVER");
        })
        .build();
  }

}
