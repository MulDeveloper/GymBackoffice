package dev.muldev.appgestiongym.clientlogin.infrastructure;

import dev.muldev.appgestiongym.clientlogin.domain.ServiceClientLogin;
import dev.muldev.appgestiongym.rabbitmq.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ClientDeletedEventConsumer {

    private final ServiceClientLogin serviceClientLogin;

    public ClientDeletedEventConsumer(ServiceClientLogin serviceClientLogin) {
        this.serviceClientLogin = serviceClientLogin;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void consumeMessageFromQueue(String username) {
        //delete
        serviceClientLogin.delete(username);
    }

}
