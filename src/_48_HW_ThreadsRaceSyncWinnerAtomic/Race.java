package _48_HW_ThreadsRaceSyncWinnerAtomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    public int min_sleep;
    public int max_sleep;
    public int distance;
    public AtomicInteger winnerId = new AtomicInteger(0);

    public Race(int min_sleep, int max_sleep, int distance) {
        super();
        this.min_sleep = min_sleep;
        this.max_sleep = max_sleep;
        this.distance = distance;
    }

}
