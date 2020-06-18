package _44_HW_ThreadsRace;

import _44_HW_ThreadsRace.item.ThreadsRaceStartItem;

public class ThreadsRace extends Thread {
    private int period;
    public String name;
    private int iterations;

    public ThreadsRace(String name, int period, int iterations) {
        this.period = period;
        this.name = name;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            try {
                sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Iteration " + (i + 1) + " " + name);
        }
        ThreadsRaceStartItem.done();
    }

}
