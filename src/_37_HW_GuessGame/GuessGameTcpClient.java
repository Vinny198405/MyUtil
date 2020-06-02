package _37_HW_GuessGame;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class GuessGameTcpClient implements GuessGame {
    private PrintStream writer;
    private BufferedReader reader;
    private int port;
    private String host;
    private Socket socket;

    public GuessGameTcpClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    @Override
    public String startGame() {
        try {
            socket = new Socket(host, port);
            writer = new PrintStream(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return getResponse("startGame", " ");
        } catch (UnknownHostException e) {
            throw new RuntimeException("Start game...unknown host " + host);
        } catch (IOException e) {
            throw new RuntimeException("Start game..." + e.getMessage());
        }
    }

    @Override
    public String prompt() {
        return getResponse("prompt", " ");
    }

    @Override
    public String move(String userInput) {
        return getResponse("move", userInput);
    }

    @Override
    public Boolean isFinished() {
        String response = getResponse("isFinished", " ");
        boolean res = response.equals("true");
        if (res) {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException("Closing socket..." + e.getMessage());
            }
        }
        return res;
    }

    private String getResponse(String headers, String payload) {
        writer.println(headers + "#" + payload);
        try {
            String res = reader.readLine();
            if (res.contains("Unknown Request")) {
                throw new RuntimeException(headers + "..." + "Unknown request");
            }
            return res;
        } catch (IOException e) {
            throw new RuntimeException(headers + "..." + e.getMessage());
        }
    }
}
