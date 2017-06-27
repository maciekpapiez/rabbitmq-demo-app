package pl.xsolve.rabbitmqdemo;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

}
