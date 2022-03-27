package edu.socket;

import lombok.Value;
import java.io.Serial;
import java.io.Serializable;

@Value
public class ObjectMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = -7176981970341160362L;

    Action action;

    Serializable data;
}
