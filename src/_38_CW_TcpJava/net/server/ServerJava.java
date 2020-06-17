package _38_CW_TcpJava.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerJava implements Runnable {
    private ProtocolJava protocol;
    private int port;
    ServerSocket serverSocket;

    public ServerJava(ProtocolJava protocol, int port) {
        this.protocol = protocol;
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                ServerClientJava client = new ServerClientJava(socket, protocol);
                Thread thread = new Thread(client);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
