package edu;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.net.Socket;


@Slf4j
public class SocketReader implements Closeable {

    private final char[] buff = new char[512];

    private final BufferedReader reader;

    private final String hostName;

    private final int port;

    @SneakyThrows
    public SocketReader(Socket socket) {
        hostName = socket.getInetAddress().getHostName();
        port = socket.getPort();
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @SneakyThrows
    public synchronized String readAll() {
        log.info("Reading data from Socket(hostName={}, port={})", hostName, port);
        var acc = new StringBuilder();
        int count;
        while ((count = reader.read(buff)) != -1) {
            acc.append(buff, 0, count);
        }
        log.info("Read {} chars from Socket(hostName={}, port={})", acc.length(), hostName, port);
        return acc.toString();
    }

    @Override
    @SneakyThrows
    public void close() {
        reader.close();
    }
}
