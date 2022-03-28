package game;

import edu.socket.SocketIO;
import lombok.*;


@Data
@RequiredArgsConstructor
public class Game {

    @NonNull
    SocketIO crossPlayer;

    @NonNull
    SocketIO circlePlayer;

    @NonNull
    Field field;

    Paint turn = Paint.CROSS;

    public static Game ofRandomly(SocketIO p1IO, SocketIO p2IO) {
        if (Math.random() < 0.5) {
            return new Game(p1IO, p2IO, new Field());
        } else {
            return new Game(p2IO, p1IO, new Field());
        }
    }

    public SocketIO getCurrentPlayer() {
        return switch (turn) {
            case CROSS -> crossPlayer;
            case CIRCLE -> circlePlayer;
            case NONE -> throw new IllegalStateException("Illegal player role");
        };
    }

    public SocketIO getNextPlayer() {
        return switch (turn) {
            case CROSS -> circlePlayer;
            case CIRCLE -> crossPlayer;
            case NONE -> throw new IllegalStateException("Illegal player role");
        };
    }

    public void flipTurn(){
        turn = switch (turn){
            case CROSS -> Paint.CIRCLE;
            case CIRCLE -> Paint.CROSS;
            case NONE -> throw new IllegalStateException("Illegal turn");
        };
    }

    public void paintCell(int pos){
        field.paintCell(pos, turn);
    }

    public VictorySet winner(){
        return field.winner();
    }
}
