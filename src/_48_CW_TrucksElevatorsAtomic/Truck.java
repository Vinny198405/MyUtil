package _48_CW_TrucksElevatorsAtomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Truck extends Thread {
    private int load;
    private int nLoads;
    private static AtomicInteger elevator1 = new AtomicInteger(0);

    private static AtomicInteger elevator2 = new AtomicInteger(0);
    public Truck(int load, int nLoads) {
        super();
        this.load = load;
        this.nLoads = nLoads;
    }
    public static int getElevator1() {
        return elevator1.get();
    }
    public static int getElevator2() {
        return elevator2.get();
    }
    @Override
    public void run() {
        for (int i = 0; i < nLoads; i++) {
            loadElev1(load);
            loadElev2(load);
        }
    }
    static private void loadElev2(int load) {
        elevator2.getAndAdd(load);
    }
    static  private void loadElev1(int load) {
        elevator1.addAndGet(load);
    }
}
