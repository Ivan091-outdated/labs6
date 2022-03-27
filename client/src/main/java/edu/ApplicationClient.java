package edu;

import edu.socket.SocketReader;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import java.net.Socket;


@SpringBootApplication
@Slf4j
public class ApplicationClient implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationClient.class).run(args);
    }

    @Override
    @SneakyThrows
    public void run(String... args) {
        var socket = new Socket("localhost", 6666);
        var reader = new SocketReader(socket);
        while (true){
            log.info("{}", reader.readObject().toString());
        }
    }
}
