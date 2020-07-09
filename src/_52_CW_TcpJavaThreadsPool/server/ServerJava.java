package _52_CW_TcpJavaThreadsPool.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerJava implements Runnable {
    private ProtocolJava protocol;
    private int port;
    private ServerSocket serverSocket;
    private ExecutorService threadsPool;

    public ServerJava(ProtocolJava protocol, int port, int nThreads) {
        threadsPool = Executors.newFixedThreadPool(nThreads);
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
                threadsPool.execute(client);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
