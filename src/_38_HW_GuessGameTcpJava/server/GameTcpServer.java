package _38_HW_GuessGameTcpJava.server;

import _37_HW_GuessGame.Logs;
import _38_HW_GuessGameTcpJava.interfece.GuessGameInterface;

import java.util.Arrays;
import java.util.Random;

public class GameTcpServer implements GuessGameInterface {
    private static final int NUMBER_LENGTH = 4;
    private static Boolean checkScanner;
    private int[] number;

    public GameTcpServer() {
    }

    @Override
    public String startGame() {
        generateNumber();
        checkScanner = false;
        return getNumber();
    }

    @Override
    public String prompt() {
        return "Enter number of 4 unrepeated digits [1-9]:";
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

    private String getNumber() {
        StringBuilder res = new StringBuilder();
        Arrays.stream(number).boxed().forEach(n -> res.append(n.toString()));
        return res.toString();
    }
}
