package edu.socket.message.data;

import game.*;
import lombok.Value;
import java.io.Serial;
import java.io.Serializable;


@Value
public class InitGameData implements Serializable {

    @Serial
    private static final long serialVersionUID = -3868591390198047120L;

    Field field;

    Paint role;
}
