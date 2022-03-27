package edu.socket;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.net.Socket;


@Slf4j
public class SocketReader {

    private final char[] buff = new char[512];

    private final Socket socket;

    private final ObjectInputStream reader;

    @SneakyThrows
    public SocketReader(Socket socket) {
        this.socket = socket;
        reader = new ObjectInputStream(socket.getInputStream());
    }

    @SneakyThrows
    public synchronized Object readObject() {
        var hostName = socket.getInetAddress().getHostAddress();
        var port = socket.getPort();
        log.info("Reading data from Socket(hostName={}, port={})", hostName, port);
        Object obj = reader.readObject();
        log.info("Read object from Socket(hostName={}, port={})", hostName, port);
        return obj;
    }
}
