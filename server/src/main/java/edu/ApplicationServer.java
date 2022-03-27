package edu;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class ApplicationServer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ApplicationServer.class).run(args);
    }
}
