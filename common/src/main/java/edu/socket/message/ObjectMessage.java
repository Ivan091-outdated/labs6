package edu.socket.message;

import lombok.Value;
import java.io.Serial;
import java.io.Serializable;


@Value
public class ObjectMessage<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -7864572448751068644L;

    Action action;

    T data;
}
