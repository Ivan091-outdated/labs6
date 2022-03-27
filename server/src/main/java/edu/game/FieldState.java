package edu.game;

import lombok.Value;
import java.util.Arrays;

@Value
public class FieldState {
    CellState[] fieldState = new CellState[9];

    public FieldState() {
        Arrays.fill(fieldState, CellState.NONE);
    }
}
