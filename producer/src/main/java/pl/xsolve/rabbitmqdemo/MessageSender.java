package pl.xsolve.rabbitmqdemo;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.xsolve.rabbitmqdemo.dto.Message;

@Service
@AllArgsConstructor
@Log4j
public class MessageSender {

    private final MessageGenerator generator;
    private final RabbitTemplate rabbitTemplate;

    @Scheduled(initialDelay = 3000L, fixedDelay = 500L)
    public void sendMessage() {
        final Message message = generator.generate();

        rabbitTemplate.convertAndSend(message);
        log.info("Message with ID " + message.getId() + " has been sent.");
    }
}
