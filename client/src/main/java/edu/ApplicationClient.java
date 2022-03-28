package edu;

import edu.socket.SocketIO;
import edu.socket.message.Action;
import edu.socket.message.data.InitGameData;
import edu.socket.message.data.TurnMade;
import game.Field;
import game.Paint;
import javafx.application.Application;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
@Slf4j
public class ApplicationClient {

    public static void main(String[] args) {
        Application.launch(FXApplication.class);
    }
}
