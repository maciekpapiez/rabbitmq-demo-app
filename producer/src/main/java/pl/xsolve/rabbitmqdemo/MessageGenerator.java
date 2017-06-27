package pl.xsolve.rabbitmqdemo;

import com.github.javafaker.Faker;
import lombok.Getter;
import org.springframework.stereotype.Service;
import pl.xsolve.rabbitmqdemo.dto.Message;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MessageGenerator {

    private final AtomicInteger counter;
    private final String name;

    public MessageGenerator() {
        this.counter = new AtomicInteger();
        this.name = new Faker().firstName();
    }

    public Message generate() {
        return Message.builder()
                .id(counter.incrementAndGet())
                .name(name)
                .build();
    }
}
