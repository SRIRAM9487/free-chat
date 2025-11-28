package com.arch.micro_service.auth_server.message.application.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

  @Bean
  public TopicExchange authExchange() {
    return new TopicExchange("auth.exchange");
  }

  @Bean
  public Queue emailQueue() {
    return new Queue("auth.email.verification.queue", true);
  }

  @Bean
  public Binding emailBinding() {
    return BindingBuilder
        .bind(emailQueue())
        .to(authExchange())
        .with("auth.email.verification");
  }

  @Bean
  public Queue passwordResetQueue() {
    return new Queue("auth.password.reset.queue", true);
  }

  @Bean
  public Binding passwordResetBinding() {
    return BindingBuilder
        .bind(passwordResetQueue())
        .to(authExchange())
        .with("auth.password.reset");
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
