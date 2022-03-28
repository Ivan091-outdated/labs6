package edu.socket.message.data;

import game.Field;
import game.VictorySet;
import lombok.Value;
import java.io.Serial;
import java.io.Serializable;


@Value
public class WinPos implements Serializable {

    @Serial
    private static final long serialVersionUID = -2951714289268192466L;

    int pos1;

    int pos2;

    int pos3;

    Field field;

    public static WinPos ofVictorySet(VictorySet victorySet, Field field){
        return new WinPos(victorySet.getPos1(), victorySet.getPos2(), victorySet.getPos3(), field);
    }
}
