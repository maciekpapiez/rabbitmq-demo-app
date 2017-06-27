package pl.xsolve.rabbitmqdemo;

import org.springframework.stereotype.Service;
import pl.xsolve.rabbitmqdemo.dto.Message;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MessageGenerator {

    private final AtomicInteger counter;

    public MessageGenerator() {
        this.counter = new AtomicInteger();
    }

    public Message generate() {
        return Message.builder()
                .id(counter.incrementAndGet())
                .build();
    }
}
