package BullsAndCows;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.*;

public class BullAndCows {
    private static final int NUMBER_LENGTH = 4;
    private static Boolean checkScanner = true;
    private int[] number;
    private List<Log> listLog = new ArrayList<>();
    private final static String FILE_LOG = "src\\BullsAndCows\\log\\";

    public BullAndCows() {
    }

    public void run() throws FileNotFoundException {
        number = generateNumber();
        playerMove();
        System.out.printf("The game is solved in %d moves, a given number: %s", listLog.size(), getNumber());
        save();
    }

    private void playerMove() {
        Scanner scanner = new Scanner(System.in);
        while (checkScanner) {
            System.out.println("Enter a four-digit number:");
            String line = scanner.nextLine();
            if (line.equals("0000")) System.out.println("Debug mode is active. Set number: " + getNumber());
            else checkScanner = checkNamber(line);
        }
    }

    private Boolean checkNamber(String strNumbers) {
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
        listLog.add(new Log(countCows, countBulls, strNumbers));
        listLog.forEach(log -> System.out.printf("player previous move: %s (%d cows, %d bulls)\n ",
                log.getNumber(), log.getCows(), log.getBulls()));
        return countBulls != length;
    }


    private int[] generateNumber() {
        return new Random().ints(1, 10)
                .distinct()
                .limit(NUMBER_LENGTH).toArray();
    }

    private void save() throws FileNotFoundException {
        String numberSet = getNumber();
        PrintStream writer = new PrintStream(getFileName());
        writer.println("Set number: " + numberSet);
        listLog.forEach(log -> writer.printf("Number: %s Cows: %d Bulls: %d\n",
                log.getNumber(), log.getCows(), log.getBulls()));
        writer.println("Total turns: " + listLog.size());
        writer.close();
    }

    private String getNumber() {
        StringBuilder res = new StringBuilder();
        Arrays.stream(number).boxed().forEach(n -> res.append(n.toString()));
        return res.toString();
    }

    private String getFileName() {
        LocalDateTime date = LocalDateTime.now();
        return String.format("%s%d-%d-%d_%d_%d_%d.txt", FILE_LOG, date.getYear(), date.getMonthValue(),
                date.getDayOfMonth(), date.getHour(), date.getMinute(), listLog.size());
    }
}
