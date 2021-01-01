package dev.muldev.appgestiongym.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitEmailConfig {

    public static final String QUEUE_EMAIL = "muldev_queue_email";
    public static final String TOPIC_EXCHANGE_EMAIL = "muldev_exchange_email";
    public static final String ROUTING_KEY_EMAIL = "muldev.user.1.event.user.sendmail";

    @Bean
    public Queue queueEmail(){
        return new Queue(QUEUE_EMAIL);
    }
    @Bean
    public TopicExchange topicExchangeEmail(){
        return new TopicExchange(TOPIC_EXCHANGE_EMAIL);
    }
    @Bean
    public Binding bindingEmail(){
        return BindingBuilder.bind(queueEmail()).to(topicExchangeEmail()).with(ROUTING_KEY_EMAIL);
    }

    @Bean
    public MessageConverter converterEmail() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate templateEmail(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converterEmail());
        return rabbitTemplate;
    }
}