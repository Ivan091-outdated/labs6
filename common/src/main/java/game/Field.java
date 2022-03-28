package game;

import lombok.Value;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;


@Value
public class Field implements Serializable {

    @Serial
    private static final long serialVersionUID = -7868114096693481867L;

    Paint[] fieldState = new Paint[9];

    public Field() {
        Arrays.fill(fieldState, Paint.NONE);
    }

    public void paintCell(int pos, Paint paint) {
        fieldState[pos] = paint;
    }

    public VictorySet winner() {
        if (Arrays.stream(fieldState).noneMatch(x -> x.equals(Paint.NONE))) {
            return new VictorySet(Winner.DRAW, -1, -1, -1);
        }
        return Stream.of(equals3NotNone(0, 1, 2), equals3NotNone(3, 4, 5), equals3NotNone(6, 7, 8),
                        equals3NotNone(0, 3, 6), equals3NotNone(1, 4, 7), equals3NotNone(2, 5, 8),
                        equals3NotNone(0, 4, 8), equals3NotNone(2, 4, 6))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseGet(() -> new VictorySet(Winner.NONE, -1, -1, -1));
    }

    public Optional<VictorySet> equals3NotNone(int pos1, int pos2, int pos3) {
        if (fieldState[pos1].equals(fieldState[pos2]) && fieldState[pos2].equals(fieldState[pos3]) && !fieldState[pos1].equals(Paint.NONE)) {
            return Optional.of(new VictorySet(fieldState[pos1].winner, pos1, pos2, pos3));
        } else {
            return Optional.empty();
        }
    }
}
