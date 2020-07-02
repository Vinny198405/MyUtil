package _48_HW_ThreadsRaceResultsTabLock;

import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Race {
    public int min_sleep;
    public int max_sleep;
    public int distance;
    public ArrayList<Racer> results;
    public Instant start;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public Lock writeLock = lock.writeLock();

    public Race(int min_sleep, int max_sleep, int distance) {
        super();
        this.min_sleep = min_sleep;
        this.max_sleep = max_sleep;
        this.distance = distance;
    }
}
