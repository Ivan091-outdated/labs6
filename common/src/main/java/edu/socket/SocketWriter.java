package edu.socket;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.net.Socket;


@Slf4j
public class SocketWriter implements Closeable {

    private final Socket socket;

    private final ObjectOutputStream writer;

    @SneakyThrows
    public SocketWriter(Socket socket) {
        this.socket = socket;
        writer = new ObjectOutputStream(socket.getOutputStream());
    }

    @SneakyThrows
    public void writeObject(Object obj) {
        var hostName = socket.getInetAddress().getHostAddress();
        var port = socket.getPort();
        log.info("Writing object to Socket(hostName={}, port={})", hostName, port);
        writer.writeObject(obj);
        writer.flush();
        log.info("Wrote object to Socket(hostName={}, port={})", hostName, port);
    }

    @Override
    @SneakyThrows
    public void close() {
        writer.close();
        socket.close();
    }
}
