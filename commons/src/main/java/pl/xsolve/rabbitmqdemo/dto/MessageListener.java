package pl.xsolve.rabbitmqdemo.dto;

import java.io.IOException;

public interface MessageListener {

    void handleMessage(Message message) throws IOException;

}
