package pl.xsolve.rabbitmqdemo;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.xsolve.rabbitmqdemo.dto.Message;
import pl.xsolve.rabbitmqdemo.dto.QueueDictionary;

@Service
@AllArgsConstructor
@Log4j
public class MessageSender {

    private final MessageGenerator generator;
    private final RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 500L)
    public void sendMessage() {
        final Message message = generator.generate();

        rabbitTemplate.convertAndSend(QueueDictionary.EXCHANGE_NAME, QueueDictionary.ROUTING_KEY, message);

        log.info(message.getName() + " sent message ID " + message.getId() + ".");
    }
}
