package edu.server;

import edu.socket.SocketIO;
import edu.socket.message.Action;
import edu.socket.message.data.*;
import game.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.net.ServerSocket;


@Component
@Slf4j
public class Server {

    public Game game;

    @SneakyThrows
    @PostConstruct
    public void init() {
        var serverSocket = new ServerSocket(6666);
        var p1IO = new SocketIO(serverSocket);
        log.info("P1 connected");
        var p2IO = new SocketIO(serverSocket);
        log.info("P2 connected");
        initGame(p1IO, p2IO);
        var gameGoing = true;
        while (gameGoing) {
            var victorySet = game.winner();
            switch (victorySet.getPaint()){
                case CROSS -> {
                    game.getCrossPlayer().writeMessage(Action.YOU_WON, WinPos.ofVictorySet(victorySet, game.getField()));
                    game.getCirclePlayer().writeMessage(Action.YOU_LOST, WinPos.ofVictorySet(victorySet, game.getField()));
                    gameGoing = false;
                }
                case CIRCLE -> {
                    game.getCrossPlayer().writeMessage(Action.YOU_LOST, WinPos.ofVictorySet(victorySet, game.getField()));
                    game.getCirclePlayer().writeMessage(Action.YOU_WON, WinPos.ofVictorySet(victorySet, game.getField()));
                    gameGoing = false;
                }
                case NONE -> {
                    handleTurn();
                    log.info("Turn handled");
                }
            }
        }
    }

    private void handleTurn(){
        var currentPlayer = game.getCurrentPlayer();
        currentPlayer.writeMessage(Action.YOUR_TURN, game.getField());
        var pos = currentPlayer.<TurnMade>readMessage().getData().getPosition();
        game.paintCell(pos);
        game.flipTurn();
    }

    private void initGame(SocketIO p1IO, SocketIO p2IO) {
        game = Game.ofRandomly(p1IO, p2IO);
        game.getCrossPlayer().writeMessage(Action.START_GAME, new InitGameData(game.getField(), Paint.CROSS));
        game.getCirclePlayer().writeMessage(Action.START_GAME, new InitGameData(game.getField(), Paint.CIRCLE));
    }
}
