package com.arch.micro_service.auth_server.security.web;

import com.arch.micro_service.auth_server.security.handler.AccessDeniedHandlerImpl;
import com.arch.micro_service.auth_server.security.handler.AuthenticationEntryPointImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final AccessDeniedHandlerImpl accessDeniedHandler;
  private final AuthenticationEntryPointImpl authenticationEntryPoint;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

    httpSecurity.cors(cors -> cors.disable());
    httpSecurity.csrf(csrf -> csrf.disable());
    httpSecurity.formLogin(form -> form.disable());
    httpSecurity.logout(log -> log.disable());

    httpSecurity.exceptionHandling(exeception -> exeception.accessDeniedHandler(accessDeniedHandler)
        .authenticationEntryPoint(authenticationEntryPoint));

    return httpSecurity.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
