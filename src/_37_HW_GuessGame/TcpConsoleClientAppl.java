package _37_HW_GuessGame;

import java.io.*;

public class TcpConsoleClientAppl {
    private static boolean flTest;
    private static String[] listLog = new String[0];
    private static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        flTest = args.length > 0 && args[0].equalsIgnoreCase("test");
        reader = new BufferedReader(new InputStreamReader(System.in));
        while (startGame()) ;
    }

    private static boolean startGame() throws IOException {
        String request = "";
        GuessGameTcpClient guessGame = new GuessGameTcpClient(4000, "localhost");

        if (guessGame.startGame().equals("startGame")) {
            if (flTest) System.out.println(guessGame.getNumber());
            while (!guessGame.isFinished()) {
                System.out.println("Enter a four-digit number:");
                request = reader.readLine();
                String response = guessGame.move(request);
                if (response.equals("false")) {
                    listLog = guessGame.prompt().split("#");
                    displayLog(listLog);
                } else break;
            }
        } else System.out.println("Server error connect");
        System.out.printf("The game is solved in %d moves, a given number: %s", listLog.length, request);
        guessGame.finishGame();
        System.out.println("Do you want to start new game? Y/N");
        String response = reader.readLine();
        return response.equalsIgnoreCase("Y");
    }

    private static void displayLog(String[] listLog) {
        for (int i = 0; i < listLog.length - 1; i++) {
            System.out.println(listLog[i]);
        }
    }
}
