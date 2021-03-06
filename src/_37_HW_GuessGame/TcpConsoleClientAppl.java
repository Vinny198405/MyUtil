package _37_HW_GuessGame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TcpConsoleClientAppl {
    private static boolean flTest;
    private static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        flTest = args.length > 0 && args[0].equalsIgnoreCase("test");
        reader = new BufferedReader(new InputStreamReader(System.in));
        while (startGame()) ;
    }

    private static boolean startGame() throws IOException {
        String request = "";
        GuessGame guessGame = new GuessGameTcpClient(4000, "localhost");
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
