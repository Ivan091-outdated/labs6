package game;

import lombok.Value;
import java.io.Serial;
import java.io.Serializable;


@Value
public class Player implements Serializable {

    @Serial
    private static final long serialVersionUID = -6906456595226122909L;

    CellState role;
}
