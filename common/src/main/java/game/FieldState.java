package game;

import lombok.Value;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

@Value
public class FieldState implements Serializable {

    @Serial
    private static final long serialVersionUID = -7868114096693481867L;

    CellState[] fieldState = new CellState[9];

    public FieldState() {
        Arrays.fill(fieldState, CellState.NONE);
    }
}
