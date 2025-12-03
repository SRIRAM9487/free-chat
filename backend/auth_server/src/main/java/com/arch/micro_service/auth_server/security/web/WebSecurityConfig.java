package com.arch.micro_service.auth_server.security.web;

import com.arch.micro_service.auth_server.security.constant.PathConstant;
import com.arch.micro_service.auth_server.security.handler.AccessDeniedHandlerImpl;
import com.arch.micro_service.auth_server.security.handler.AuthenticationEntryPointImpl;
import com.arch.micro_service.auth_server.security.web.filter.ExceptionalHandlerFilter;
import com.arch.micro_service.auth_server.security.web.filter.JwtFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
// @EnableMethodSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final AccessDeniedHandlerImpl accessDeniedHandler;
  private final AuthenticationEntryPointImpl authenticationEntryPoint;
  private final JwtFilter jwtFilter;
  private final ExceptionalHandlerFilter exceptionalHandlerFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .logout(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable);

    httpSecurity.authorizeHttpRequests(http -> http
        .requestMatchers(HttpMethod.GET, PathConstant.GET_PATHS)
        .permitAll()
        .requestMatchers(HttpMethod.POST, PathConstant.POST_PATHS)
        .permitAll()
        .anyRequest()
        .permitAll());

    httpSecurity.exceptionHandling(exeception -> exeception
        .accessDeniedHandler(accessDeniedHandler)
        .authenticationEntryPoint(authenticationEntryPoint));

    httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    httpSecurity.addFilterBefore(exceptionalHandlerFilter, JwtFilter.class);
    return httpSecurity.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
