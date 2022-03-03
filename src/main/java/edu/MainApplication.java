package edu;

import javafx.application.Application;
import org.h2.tools.Server;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.sql.SQLException;


@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        Application.launch(FXApplication.class);
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }
}
