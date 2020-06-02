package _37_HW_GuessGame;

import java.io.*;
import java.net.*;

public class TcpBullsCowsAppl {
    private static final int PORT = 4000;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is listening on port " + PORT);
        BullsCowsGameTcpServer bullsCows = new BullsCowsGameTcpServer();
        while (true) {
            Socket socket = serverSocket.accept();
            bullsCows.runClient(socket);
        }
    }
}
