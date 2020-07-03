package _48_HW_ThreadsRaceResultsTabLock;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Racer extends Thread {
    private int threadId;
    private final Race race;
    private volatile long runTime;


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
        runTime = ChronoUnit.MILLIS.between(race.start, Instant.now());
        try {
            race.lock.lock();
            race.results.add(this);
        } finally {
            race.lock.unlock();
        }
    }

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int id) {
        this.threadId = id;
    }

    public long getRunTime() {
        return runTime;
    }
}

