package edu.socket.message;

import edu.socket.message.data.*;
import game.Field;


public enum Action {
    START_GAME(InitGameData.class),
    YOUR_TURN(Field.class),
    TURN_MADE(TurnMade.class),
    YOU_WON(WinPos.class),
    YOU_LOST(WinPos.class),
    DRAW(Field.class);

    public final Class<?> aClass;

    Action(Class<?> aClass) {
        this.aClass = aClass;
    }
}
