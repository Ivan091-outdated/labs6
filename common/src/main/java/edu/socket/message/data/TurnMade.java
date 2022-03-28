package edu.socket.message.data;

import lombok.Value;
import java.io.Serial;
import java.io.Serializable;

@Value
public class TurnMade implements Serializable {

    @Serial
    private static final long serialVersionUID = -6931647523877572774L;

    int position;
}
