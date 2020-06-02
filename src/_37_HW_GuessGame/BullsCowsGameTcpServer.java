package _37_HW_GuessGame;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class BullsCowsGameTcpServer implements GuessGame {
    private static final int NUMBER_LENGTH = 4;
    private static Boolean checkScanner;
    private int[] number;

    BullsCowsGameTcpServer() {
        generateNumber();
    }

    @Override
    public String startGame() {
        checkScanner = false;
        return "startGame";
    }

    @Override
    public String prompt() {
        return "Enter a four-digit number:";
    }

    @Override
    public String move(String userInput) {
        return checkNumber(userInput);
    }

    @Override
    public Boolean isFinished() {
        return checkScanner;
    }

    private String checkNumber(String strNumbers) {
        int length = Math.min(strNumbers.length(), NUMBER_LENGTH);
        int countCows = 0;
        int countBulls = 0;
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            for (int j = 0; j < length; j++) {
                int num = Character.getNumericValue(strNumbers.charAt(j));
                if (number[i] == num) {
                    if (i == j) {
                        countBulls++;
                    } else countCows++;
                }
            }
        }
        checkScanner = countBulls == length;
        return new Logs(countCows, countBulls, strNumbers).toString();
    }


    private void generateNumber() {
        number = new Random().ints(1, 10)
                .distinct()
                .limit(NUMBER_LENGTH).toArray();
    }

    public String getNumber() {
        StringBuilder res = new StringBuilder();
        Arrays.stream(number).boxed().forEach(n -> res.append(n.toString()));
        return res.toString();
    }

    void runClient(Socket socket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintStream writer = new PrintStream(socket.getOutputStream())) {
            while (true) {
                String request = reader.readLine();
                if (request == null || request.equals("finishGame")) {
                    break;
                }
                String response = getResponse(request);
                writer.println(response);
            }
        } catch (IOException e) {
            System.out.println("illegal way of closing connection");
            return;
        }
        System.out.println("client closed connection");
    }

    private String getResponse(String request) {
        String[] headersPayload = request.split("#");
        String headers = headersPayload[0];
        String payload = headersPayload[1];
        if (headersPayload.length != 2) return "Unknown Request";
        switch (headers) {
            case "startGame":
                return startGame();
            case "move":
                return move(payload);
            case "isFinished":
                return isFinished().toString();
            case "prompt":
                return prompt();
            case "getNumber":
                return getNumber();
            default:
                return "Unknown Request";
        }
    }


}