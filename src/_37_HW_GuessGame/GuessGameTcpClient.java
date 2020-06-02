package _37_HW_GuessGame;

import java.io.*;
import java.net.Socket;

public class GuessGameTcpClient implements GuessGame {
    private PrintStream writer;
    private BufferedReader reader;
    private int PORT;
    private String HOST;

    public GuessGameTcpClient(int PORT, String HOST) throws IOException {
        this.PORT = PORT;
        this.HOST = HOST;
    }

    @Override
    public String startGame() throws IOException {
        Socket socket = new Socket(HOST, PORT);
        writer = new PrintStream(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer.println("startGame# ");
        return reader.readLine();
    }

    @Override
    public String prompt() throws IOException {
        writer.println("prompt# ");
        return reader.readLine();
    }

    @Override
    public String move(String userInput) throws IOException {
        writer.println("move#" + userInput);
        return reader.readLine();
    }

    @Override
    public Boolean isFinished() throws IOException {
        String response = "";
        writer.println("isFinished# ");
        response = reader.readLine();
        return response.equals("true");
    }

    public String getNumber() throws IOException {
        writer.println("getNumber# ");
        return reader.readLine();
    }

    public void finishGame() {
        writer.println("finishGame");
    }
}
