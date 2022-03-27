package edu.server;

import edu.socket.*;
import game.*;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.net.ServerSocket;
import java.net.Socket;


@Component
@Slf4j
public class Server {

    @SneakyThrows
    @PostConstruct
    public void init() {
        var serverSocket = new ServerSocket(6666);
        var p1Socket = serverSocket.accept();
        log.info("P1 connected");
        var p2Socket = serverSocket.accept();
        log.info("P2 connected");
        var p1Writer =  new SocketWriter(p1Socket);
        var p2Writer = new SocketWriter(p2Socket);
        var p1Reader = new SocketReader(p1Socket);
        var p2Reader = new SocketReader(p2Socket);
        var game = generateGame();
        p1Writer.writeObject(new ObjectMessage(Action.START_GAME, game.getP1()));
        p2Writer.writeObject(new ObjectMessage(Action.START_GAME, game.getP2()));
        switch (game.getTurn()) {
            case P1 -> p1Writer.writeObject(new ObjectMessage(Action.YOUR_TURN, null));
            case P2 -> p2Writer.writeObject(new ObjectMessage(Action.YOUR_TURN, null));
        }
    }

    public Game generateGame() {
        if (Math.random() < 0.5) {
            return new Game(new Player(CellState.CIRCLE), new Player(CellState.CROSS), new FieldState(), Turn.P2);
        } else {
            return new Game(new Player(CellState.CROSS), new Player(CellState.CIRCLE), new FieldState(), Turn.P1);
        }
    }

    @AllArgsConstructor
    public static class ConnectionHandler implements Runnable {

        private final Socket socket;

        @Override
        public void run() {
        }
    }
}
