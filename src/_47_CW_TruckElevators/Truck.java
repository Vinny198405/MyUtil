package _47_CW_TruckElevators;

public class Truck extends Thread {
    private int load;
    private int nLoads;
    private static int elevator1;
    private static int elevator2;
    private static final Object mutex = new Object();

    public Truck(int load, int nLoads) {
        this.load = load;
        this.nLoads = nLoads;
    }

    @Override
    public void run() {
        for (int i = 0; i < nLoads; i++) {
            loadElev1(load);
            loadElev2(load);
        }
    }

    static synchronized private void loadElev1(int load) {
        elevator1 += load;
    }

    static private void loadElev2(int load) {
        synchronized (mutex) {
            elevator2 += load;
        }
    }

    public static int getElevator1() {
        return elevator1;
    }

    public static int getElevator2() {
        return elevator2;
    }
}
