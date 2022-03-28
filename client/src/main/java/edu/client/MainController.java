package edu.client;

import edu.socket.SocketIO;
import edu.socket.message.Action;
import edu.socket.message.data.*;
import game.Field;
import game.Paint;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;


@Controller
@Slf4j
public class MainController {

    private final SocketIO io = new SocketIO("localhost", 6666);

    @FXML
    public GridPane grid;

    private Button[] buttons;

    private Paint role;

    @FXML
    public void initialize() {
        buttons = grid.getChildrenUnmodifiable().stream().map(x -> (Button) x).toArray(Button[]::new);
        new Thread(this::gameLoop).start();
        grid.setDisable(true);
    }

    @FXML
    public void buttonClicked(MouseEvent event) {
        var button = ((Button) event.getTarget());
        var pos = grid.getChildrenUnmodifiable().indexOf(button);
        io.writeMessage(Action.TURN_MADE, new TurnMade(pos));
        switch (role) {
            case CROSS -> button.setText("X");
            case CIRCLE -> button.setText("O");
            case NONE -> button.setText("");
        }
        button.setDisable(true);
        grid.setDisable(true);
    }

    private void gameLoop() {
        while (true) {
            var message = io.readMessage();
            var data = message.getData();
            switch (message.getAction()) {
                case START_GAME -> {
                    var castedData = (InitGameData) data;
                    var field = castedData.getField();
                    role = castedData.getRole();
                    Platform.runLater(new FieldMapper(field));
                }
                case YOUR_TURN -> {
                    Platform.runLater(new FieldMapper((Field) data));
                    grid.setDisable(false);
                }
                case YOU_WON -> {
                    var castedData = (WinPos) data;
                    var field = castedData.getField();
                    Platform.runLater(new FieldMapper(field));
                    Platform.runLater(new Victory(castedData));
                    return;
                }
                case YOU_LOST -> {
                    var castedData = (WinPos) data;
                    var field = castedData.getField();
                    Platform.runLater(new FieldMapper(field));
                    Platform.runLater(new Loss(castedData));
                    return;
                }
                default -> {
                }
            }
        }
    }

    @AllArgsConstructor
    private class Loss implements Runnable {

        WinPos victorySet;

        @Override
        public void run() {
            buttons[victorySet.getPos1()].setStyle("-fx-background-color: red");
            buttons[victorySet.getPos2()].setStyle("-fx-background-color: red");
            buttons[victorySet.getPos3()].setStyle("-fx-background-color: red");
        }
    }

    @AllArgsConstructor
    private class Victory implements Runnable {

        WinPos victorySet;

        @Override
        public void run() {
            buttons[victorySet.getPos1()].setStyle("-fx-background-color: green");
            buttons[victorySet.getPos2()].setStyle("-fx-background-color: green");
            buttons[victorySet.getPos3()].setStyle("-fx-background-color: green");
        }
    }

    @AllArgsConstructor
    private class FieldMapper implements Runnable {
        Field field;

        @Override
        public void run() {
            var state = field.getFieldState();
            for (var i = 0; i < 9; i++) {
                switch (state[i]) {
                    case CROSS -> {
                        buttons[i].setText("X");
                        buttons[i].setDisable(true);
                    }
                    case CIRCLE -> {
                        buttons[i].setText("O");
                        buttons[i].setDisable(true);
                    }
                    case NONE -> buttons[i].setText("");
                }
            }
        }
    }
}