package _49_CW_DeadLock;

public class Worker extends Thread {
    private int nRuns;
    static final Object mutex1 = new Object();
    static final Object mutex2 = new Object();

    public Worker(int nRuns) {
        this.nRuns = nRuns;
    }

    @Override
    public void run() {
        for (int i = 0; i < nRuns; i++) {
            f1();
            f2();
        }
    }

    private void f2() {
        synchronized (mutex1) {
            synchronized (mutex2){

            }
        }
    }

    private void f1() {
        synchronized (mutex2) {
            synchronized (mutex1){

            }
        }
    }
}
