package edu;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import java.io.*;
import java.net.Socket;
import java.util.stream.Collectors;


@SpringBootApplication
@Slf4j
public class ApplicationClient implements CommandLineRunner {

    public Socket socket;

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationClient.class).run(args);
    }

    @Override
    @SneakyThrows
    public void run(String... args) {
        var buff = new char[256];
        var count = 0;
        socket = new Socket("localhost", 6666);
        try (var in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            while ((count = in.read(buff)) != -1){
                log.info("Answer:" + String.valueOf(buff, 0, count));
            }
        }
        socket.close();
    }
}
