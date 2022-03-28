package edu.socket;

import edu.socket.message.Action;
import edu.socket.message.ObjectMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


@Slf4j
public class SocketIO implements Closeable {

    private final Socket socket;

    private final ObjectInputStream reader;

    private final ObjectOutputStream writer;

    @SneakyThrows
    public SocketIO(String host, int port) {
        socket = new Socket(host, port);
        writer = new ObjectOutputStream(socket.getOutputStream());
        reader = new ObjectInputStream(socket.getInputStream());
    }

    @SneakyThrows
    public SocketIO(ServerSocket serverSocket) {
        socket = serverSocket.accept();
        writer = new ObjectOutputStream(socket.getOutputStream());
        reader = new ObjectInputStream(socket.getInputStream());
    }

    public <T> void writeMessage(Action action, T data){
        writeObject(new ObjectMessage<>(action, data));
    }

    @SuppressWarnings("unchecked")
    public <T> ObjectMessage<T> readMessage(){
        return (ObjectMessage<T>) readObject();
    }

    @SneakyThrows
    public void writeObject(Object obj) {
        var hostName = socket.getInetAddress().getHostAddress();
        var port = socket.getPort();
        writer.writeObject(obj);
        writer.flush();
        writer.reset();
        log.info("Wrote object {} to Socket(hostName={}, port={})", obj, hostName, port);
    }

    @SneakyThrows
    public Object readObject() {
        var hostName = socket.getInetAddress().getHostAddress();
        var port = socket.getPort();
        Object obj = reader.readObject();
        log.info("Read object {} from Socket(hostName={}, port={})", obj, hostName, port);
        return obj;
    }

    @Override
    public void close() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }
}
