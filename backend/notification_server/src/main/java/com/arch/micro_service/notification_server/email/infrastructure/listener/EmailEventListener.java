package com.arch.micro_service.notification_server.email.infrastructure.listener;

import com.arch.micro_service.notification_server.email.infrastructure.event.EmailVerificationEvent;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailEventListener {

  @RabbitListener(queues = "auth.email.verification.queue")
  public void sendEmail(EmailVerificationEvent event) {
    for (int i = 0; i < 1; i++) {

      System.out
          .println("RABBIT MQ QUEUE MESSAGE CONSUMED  EMAIL : {" + event.email() + "}" + " TOKEN : " + event.token());
    }

  }

}
