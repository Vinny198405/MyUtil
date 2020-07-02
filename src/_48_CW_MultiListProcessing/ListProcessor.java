package _48_CW_MultiListProcessing;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ListProcessor extends Thread {
    private List<Integer> list;
    static private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();
    private static AtomicLong countBlckIterations = new AtomicLong(0);

    private int nRuns;
    private int probUpdate;

    public ListProcessor(List<Integer> list, int nRuns, int probUpdate) {
        super();
        this.list = list;
        this.nRuns = nRuns;
        this.probUpdate = probUpdate;
    }
    @Override
    public void run() {
        for (int i = 0; i < nRuns; i++) {
            if (chance() < probUpdate) {
                updateList();
            } else {
                readList();
            }
        }
    }
    private void readList() {
        try {
            tryLockCounting(readLock);
            list.get(list.size() - 1);
            list.get(list.size() - 1);
        } finally {
            readLock.unlock();
        }
    }
    private void updateList() {
        try {
            tryLockCounting(writeLock);
            list.remove(list.size() - 1);
            list.add(100);
        } finally {
            writeLock.unlock();
        }

    }
    private void tryLockCounting(Lock lock) {
        while(!lock.tryLock()) {
            countBlckIterations.getAndIncrement();
        }

    }
    private int chance() {
        return (int) (Math.random() * 100);
    }

    public static long getCountBlckIterations() {
        return countBlckIterations.get();
    }
}

