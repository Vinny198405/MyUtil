package _48_HW_ThreadsRaceSyncWinnerAtomic;

public class Racer extends Thread {
    private int threadId;
    private final Race race;

    public Racer(int id, Race race) {
        super();
        this.threadId = id;
        this.race = race;
    }

    @Override
    public void run() {
        int sleepDelta = race.max_sleep - race.min_sleep + 1;
        for (int i = 0; i < race.distance; i++) {
            System.out.println("thread: " + threadId + " distance: " + i);
            try {
                sleep((long) (race.min_sleep + Math.random() * sleepDelta));
            } catch (InterruptedException e) {

            }
        }

        race.winnerId.compareAndSet(0, threadId);

    }

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int id) {
        this.threadId = id;
    }
}
