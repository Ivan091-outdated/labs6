package edu.socket;

import game.Player;


public enum Action {
    START_GAME(Player.class),
    YOUR_TURN(Object.class);

    public final Class<?> aClass;

    Action(Class<?> aClass) {
        this.aClass = aClass;
    }
}
