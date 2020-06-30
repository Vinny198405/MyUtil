package _47_HW_ThreadsRace;

import java.time.Instant;
import java.util.Map;

public class ThreadsRace extends Thread {
    public String name;
    private int iterations;
    private static final int MIN = 1;
    private static final int MAX = 5;
    private static String winner;
    private static Map<String, Instant> winnerMap;

    public ThreadsRace(String name, int iterations) {
        this.name = name;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            try {
                int period = getPeriod();
                System.out.println("Iteration " + (i + 1) + " " + name + " period = " + period);
                sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Instant finish = Instant.now();
        saveWinner(name, finish);
    }

    static synchronized private void saveWinner(String name, Instant finish) {
        if (winner == null) winner = name;
        winnerMap.put(name, finish);
    }

    private static int getPeriod() {
        return (int) (MIN + Math.random() * (MAX - MIN));
    }

    public static String getWinner() {
        return winner;
    }

    public static void setWinner(String winner) {
        ThreadsRace.winner = winner;
    }

    public static Map<String, Instant> getWinnerMap() {
        return winnerMap;
    }

    public static void setWinnerMap(Map<String, Instant> winnerMap) {
        ThreadsRace.winnerMap = winnerMap;
    }
}
