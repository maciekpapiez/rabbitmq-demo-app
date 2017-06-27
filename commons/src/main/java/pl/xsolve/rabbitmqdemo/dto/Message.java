package pl.xsolve.rabbitmqdemo.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
public class Message implements Serializable {

    private int id;
    private String name;

}
