package _37_HW_GuessGame;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class GuessGameTcpClient implements GuessGame {
    private PrintStream writer;
    private BufferedReader reader;
    private int port;
    private String host;

    public GuessGameTcpClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    @Override
    public String startGame() {
        try {
            Socket socket = new Socket(host, port);
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
    public Boolean isFinished() throws IOException {
        String response = "";
        Boolean res;
        writer.println("isFinished# ");
        response = reader.readLine();
        if (res = response.equals("true")) {
            writer.close();
            reader.close();
        }
        return res;
    }

    public String getNumber() {
        return getResponse("getNumber", " ");
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
