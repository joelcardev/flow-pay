package br.com.flowPay.infra;


import br.com.flowPay.dto.notification.NotificationMessage;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static final String EXCHANGE_NAME = "support.notifications.exchange";
    private static final String NEW_REQUEST_ROUTING_KEY = "notifications.new.request";

    @Bean
    public TopicExchange notificationsExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue newRequestQueue() {
        return new Queue("support.notifications.new.request.queue");
    }

    @Bean
    public Binding newRequestBinding(Queue newRequestQueue, TopicExchange notificationsExchange) {
        return BindingBuilder.bind(newRequestQueue).to(notificationsExchange).with(NEW_REQUEST_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MessageListener messageListener() {
        return message -> {
            NotificationMessage notificationMessage = (NotificationMessage) messageConverter().fromMessage(message);
        };
    }

}
