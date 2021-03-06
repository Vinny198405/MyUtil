package _48_HW_ThreadsRaceResultsTabLock;

import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Race {
    public int min_sleep;
    public int max_sleep;
    public int distance;
    public ArrayList<Racer> results;
    public Instant start;
    public final Lock lock = new ReentrantLock(true);

    public Race(int min_sleep, int max_sleep, int distance) {
        super();
        this.min_sleep = min_sleep;
        this.max_sleep = max_sleep;
        this.distance = distance;
    }
}
