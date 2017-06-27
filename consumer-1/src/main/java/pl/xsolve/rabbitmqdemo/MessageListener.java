package pl.xsolve.rabbitmqdemo;

import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.xsolve.rabbitmqdemo.dto.Message;
import pl.xsolve.rabbitmqdemo.dto.QueueDictionary;

@Log4j
@Component
public class MessageListener {

    @RabbitListener(queues = QueueDictionary.QUEUE_NAME_1)
    public void handle(final Message message) throws InterruptedException {
        Thread.sleep(500);

        log.info(String.format("Processed message ID %d from %s", message.getId(), message.getName()));
    }

}
