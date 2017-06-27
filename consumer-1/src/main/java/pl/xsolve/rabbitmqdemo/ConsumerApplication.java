package pl.xsolve.rabbitmqdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.xsolve.rabbitmqdemo.dto.MessageListener;
import pl.xsolve.rabbitmqdemo.dto.QueueDictionary;

@EnableRabbit
@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    public Exchange exchange() {
        return new FanoutExchange(QueueDictionary.EXCHANGE_NAME);
    }

    @Bean
    public Queue queue() {
        return new Queue(QueueDictionary.QUEUE_NAME_1);
    }

    @Bean
    public Binding binding(final Queue queue, final FanoutExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange);
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(final ConnectionFactory connectionFactory,
                                                                         final MessageListener messageConsumer,
                                                                         final Queue contentQueue,
                                                                         final Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);

        // we're setting here what was set previously using annotations
        container.setQueues(contentQueue);
        container.setMessageListener(new MessageListenerAdapter(messageConsumer, jackson2JsonMessageConverter));

        return container;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(final ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
