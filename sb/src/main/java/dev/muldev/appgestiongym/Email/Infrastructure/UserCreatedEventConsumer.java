package dev.muldev.appgestiongym.email.infrastructure;

import dev.muldev.appgestiongym.clients.domain.Credentials;
import dev.muldev.appgestiongym.email.domain.ServiceEmail;
import dev.muldev.appgestiongym.rabbitmq.RabbitConfig;
import dev.muldev.appgestiongym.rabbitmq.RabbitEmailConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedEventConsumer {

    private final ServiceEmail serviceEmail;

    public UserCreatedEventConsumer(ServiceEmail serviceEmail) {
        this.serviceEmail = serviceEmail;
    }

    @RabbitListener(queues = RabbitEmailConfig.QUEUE_EMAIL)
    public void consumeMessageFromQueue(Credentials credentials) {
        serviceEmail.sendEmailWithCredentials(credentials.getUsername(), credentials.getEmail(), credentials.getPassword());
    }
}
