package _44_HW_ThreadsRace;

import java.util.Scanner;

public class ThreadsRaceAppl {
    private static final int MIN = 1;
    private static final int MAX = 5;
    private static ThreadsRace winner = null;

    synchronized static void done() {
        if (winner == null) winner = (ThreadsRace) Thread.currentThread();
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of threads:");
        int line = scanner.nextInt();
        startThreads(line);
        System.out.println("Congratulations to winner " + winner.name);
    }

    private static void startThreads(int num) throws InterruptedException {
        ThreadsRace[] threadsRace = new ThreadsRace[num];
        for (int i = 0; i < num; i++) {
            threadsRace[i] = new ThreadsRace("Thread" + i, getPeriod());
            threadsRace[i].start();
        }
        for (ThreadsRace sh: threadsRace){
            sh.join();
        }
    }

    private static int getPeriod() {
        return (int) (MIN + Math.random() * (MAX - MIN));
    }
}
