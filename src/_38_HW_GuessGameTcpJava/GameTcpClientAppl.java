package _38_HW_GuessGameTcpJava;

import _38_HW_GuessGameTcpJava.client.GameTcpClient;
import _38_HW_GuessGameTcpJava.interfece.GuessGameInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GameTcpClientAppl {
    private static boolean flTest;
    private static BufferedReader reader;
    private static  final String HOST = "localhost";
    private static final int PORT = 4000;

    public static void main(String[] args) throws IOException {
        flTest = args.length > 0 && args[0].equalsIgnoreCase("test");
        reader = new BufferedReader(new InputStreamReader(System.in));
        while (startGame()) ;
    }

    private static boolean startGame() throws IOException {
        String request = "";
        GuessGameInterface guessGame = new GameTcpClient(HOST, PORT);
        List<String> listLog = new ArrayList<>();
        String number = guessGame.startGame();

        if (flTest) System.out.println(number);
        while (!guessGame.isFinished()) {
            System.out.println(guessGame.prompt());
            request = reader.readLine();
            String response = guessGame.move(request);
            displayLog(listLog, response);
        }
        return finishGame(listLog);
    }

    private static boolean finishGame(List<String> listLog) throws IOException {
        System.out.printf("The game is solved in %d moves\n", listLog.size());
        System.out.println("Do you want to start new game? Y/N");
        String response = reader.readLine();
        return response.equalsIgnoreCase("Y");
    }

    private static void displayLog(List<String> listLog, String response) {
        listLog.add(response);
        listLog.forEach(System.out::println);
    }
}
