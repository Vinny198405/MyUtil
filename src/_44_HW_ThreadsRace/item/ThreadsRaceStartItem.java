package _44_HW_ThreadsRace.item;

import _39_HW_MenuItemsInputOutput.menu.InputOutput;
import _44_HW_ThreadsRace.ThreadsRace;

public class ThreadsRaceStartItem extends ThreadsRaceItem {
    private static final int MIN = 1;
    private static final int MAX = 5;
    private static ThreadsRace winner = null;

    public ThreadsRaceStartItem(ThreadsRace threadsRace, InputOutput inputOutput) {
        super(threadsRace, inputOutput);
    }

    public synchronized static void done() {
        if (winner == null) winner = (ThreadsRace) Thread.currentThread();
    }

    @Override
    public String displayName() {
        return "Start Threads Race Game";
    }

    @Override
    public void perform() {
        winner = null;
        int threads = inputOutput.inputInteger("Enter number of threads [2-1000]:", 2, 1000);
        int distance = inputOutput.inputInteger("Enter number of distance [2-1000]:", 2, 1000);
        try {
            startThreads(threads, distance);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Congratulations to winner " + winner.name);
    }

    private void startThreads(int threads, int distance) throws InterruptedException {
        ThreadsRace[] threadsRace = new ThreadsRace[threads];
        int period = getPeriod();
        for (int i = 0; i < threads; i++) {
            threadsRace[i] = new ThreadsRace("Thread" + i, period, distance);
            threadsRace[i].start();
        }
        for (ThreadsRace sh : threadsRace) {
            sh.join();
        }
    }

    private static int getPeriod() {
        return (int) (MIN + Math.random() * (MAX - MIN));
    }
}
