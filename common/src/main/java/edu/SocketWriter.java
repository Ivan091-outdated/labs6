package edu;

import lombok.SneakyThrows;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class SocketWriter implements Closeable{

    private final DataOutputStream writer;

    private final String hostName;

    private final int port;

    @SneakyThrows
    public SocketWriter(Socket socket) {
        writer = new DataOutputStream(socket.getOutputStream());
        hostName = socket.getInetAddress().getHostName();
        port = socket.getPort();
    }

    @SneakyThrows
    public void writeAll(String s){
        writer.write(s.getBytes(StandardCharsets.UTF_8));
        writer.flush();
    }

    @SneakyThrows
    @Override
    public void close() {
        writer.close();
    }
}
