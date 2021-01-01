package dev.muldev.appgestiongym.clientlogin.infrastructure;

import dev.muldev.appgestiongym.clients.domain.Credentials;
import dev.muldev.appgestiongym.clientlogin.domain.ClientLogin;
import dev.muldev.appgestiongym.clientlogin.domain.ServiceClientLogin;
import dev.muldev.appgestiongym.rabbitmq.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClientCreatedEventConsumer {

    private final ServiceClientLogin serviceClientLogin;

    public ClientCreatedEventConsumer(ServiceClientLogin serviceClientLogin) {
        this.serviceClientLogin = serviceClientLogin;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void consumeMessageFromQueue(Credentials credentials) {
        serviceClientLogin.add(new ClientLogin(UUID.randomUUID(), credentials.getUsername(), credentials.getPassword()));
    }
}