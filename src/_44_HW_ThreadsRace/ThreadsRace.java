package _44_HW_ThreadsRace;

import _44_HW_ThreadsRace.item.ThreadsRaceStartItem;

public class ThreadsRace extends Thread {
    public String name;
    private int iterations;
    private static final int MIN = 1;
    private static final int MAX = 5;

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
        ThreadsRaceStartItem.done();
    }

    private static int getPeriod() {
        return (int) (MIN + Math.random() * (MAX - MIN));
    }

}
