package _52_CW_GroupSumPerformance.numbers;

import java.util.Arrays;
import java.util.concurrent.*;

public class ThreadsPoolGroupSum extends GroupSum {
    private int nThreads = 3;

    public ThreadsPoolGroupSum(int[][] groups) {
        super(groups);
    }

    @Override
    public Long computeSum() {
        FutureTask<Long>[] tasks = new FutureTask[groups.length];
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        fillTasks(tasks);
        startTasks(tasks, executorService);
        executorService.shutdown();
        return Arrays.stream(tasks).mapToLong(t -> {
            try {
                return t.get();
            } catch (InterruptedException | ExecutionException e) {
                return 0;
            }
        }).sum();
    }

    private void startTasks(FutureTask<Long>[] tasks, ExecutorService executorService) {
        for(FutureTask<Long> task: tasks){
            executorService.execute(task);
        }
    }

    private void fillTasks(FutureTask<Long>[] tasks) {
        for(int i = 0; i< tasks.length;i++){
            tasks[i] = new FutureTask<>(new OneGroupSum(groups[i]));
        }
    }

    public void setnThreads(int nThreads) {
        this.nThreads = nThreads;
    }
}
