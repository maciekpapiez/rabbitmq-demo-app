package pl.xsolve.rabbitmqdemo;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import pl.xsolve.rabbitmqdemo.dto.Message;
import pl.xsolve.rabbitmqdemo.dto.MessageListener;

@Log4j
@Component
public class MessageListenerImpl implements MessageListener {

    @Override
    public void handleMessage(final Message message) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info(String.format("Processed message ID %d from %s", message.getId(), message.getName()));
    }

}
