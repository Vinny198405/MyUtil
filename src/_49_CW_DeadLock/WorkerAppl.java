package _49_CW_DeadLock;

public class WorkerAppl {

    private static final int N_RUNS = 1;

    public static void main(String[] args) throws InterruptedException {
        Worker worker1 = new Worker(N_RUNS);
        Worker worker2 = new Worker(N_RUNS);
        worker1.start();
        worker2.start();

        Thread.currentThread().join();
    }
}
