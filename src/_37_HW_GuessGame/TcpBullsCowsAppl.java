package _37_HW_GuessGame;

import java.io.*;
import java.net.*;

public class TcpBullsCowsAppl {
    private static final int PORT = 4000;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is listening on port " + PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            runClient(socket);
        }
    }

    private static void runClient(Socket socket) {
        BullsCowsGameTcpServer bullsCows = new BullsCowsGameTcpServer();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintStream writer = new PrintStream(socket.getOutputStream())) {
            while (true) {
                String request = reader.readLine();
                if (request == null || request.equals("finishGame")) {
                    break;
                }
                String response = getResponse(request, bullsCows);
                writer.println(response);
            }
        } catch (IOException e) {
            System.out.println("illegal way of closing connection");
            return;
        }
        System.out.println("client closed connection");
    }

    private static String getResponse(String request, BullsCowsGameTcpServer bullsCows) {
        String[] headersPayload = request.split("#");
        String headers = headersPayload[0];
        String payload = headersPayload[1];
        if (headersPayload.length != 2) return "Unknown Request";
        switch (headers) {
            case "startGame":
                return bullsCows.startGame();
            case "move":
                return bullsCows.move(payload);
            case "isFinished":
                return bullsCows.isFinished().toString();
            case "prompt":
                return bullsCows.prompt();
            case "getNumber":
                return bullsCows.getNumber();
            default:
                return "Unknown Request";
        }
    }
}
