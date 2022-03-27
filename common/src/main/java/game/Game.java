package game;

import lombok.*;


@Data
@AllArgsConstructor
public class Game {
    Player p1;
    Player p2;
    FieldState fieldState;
    Turn turn;
}
