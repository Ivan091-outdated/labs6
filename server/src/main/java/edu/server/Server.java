package edu.server;

import edu.game.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.*;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;


@Component
@Slf4j
public class Server {

    public final int port = 6666;

    private Game game;

    private DataOutputStream p1Out;

    private BufferedReader p1In;

    private DataOutputStream p2Out;

    private BufferedReader p2In;

    @SneakyThrows
    @PostConstruct
    public void init() {
        var socket = new ServerSocket(port);
        var p1Socket = socket.accept();
        log.info("P1 connected");
        p1Out = new DataOutputStream(p1Socket.getOutputStream());
        p1In = new BufferedReader(new InputStreamReader(p1Socket.getInputStream()));
        p1Out.write("Adam".getBytes(StandardCharsets.UTF_8));
        p1Out.flush();
//        var p2Socket = socket.accept();
//        log.info("P2 connected");
//        p2Out = new DataOutputStream(p2Socket.getOutputStream());
//        p2In = new BufferedReader(new InputStreamReader(p2Socket.getInputStream()));
//        Player p1;
//        Player p2;
//        Turn turn;
//        if (Math.random() < 0.5) {
//            p1 = new Player(CellState.CIRCLE);
//            p2 = new Player(CellState.CROSS);
//            turn = Turn.P2;
//        } else {
//            p1 = new Player(CellState.CROSS);
//            p2 = new Player(CellState.CIRCLE);
//            turn = Turn.P1;
//        }
//        game = new Game(p1, p2, new FieldState(), turn);
//        startGame();
        Thread.sleep(10000000);
    }

    @SneakyThrows
    public void startGame() {
        writeToBoth("The game is about to begin.");
        p1Out.writeUTF(game.getP1().getRole().name());
        p2Out.writeUTF(game.getP2().getRole().name());
        p1Out.flush();
        p2Out.flush();
    }

    @SneakyThrows
    private void writeToBoth(String string) {
        p1Out.writeUTF(string);
        p2Out.writeUTF(string);
    }
}
