package _52_CW_GroupSumPerformance.tests;

import _52_CW_GroupSumPerformance.numbers.GroupSumStream;
import _52_CW_GroupSumPerformance.numbers.ThreadsPoolGroupSum;
import _52_CW_GroupSumPerformance.performance.PerformanceGroupSumTest;

import java.util.Random;

public class TestAppl {
    private static final int N_GROUPS = 2000;
    private static final int N_NUMBERS_GROUP = 100_000;
    private static final int N_RUNS = 10;
    private static final int[] nThreads = {1, 3, 10, 100, 1000, N_GROUPS};

    public static void main(String[] args) {
        ThreadsPoolGroupSum groupSum = new ThreadsPoolGroupSum(getGroups());
        startTest(groupSum);
        PerformanceGroupSumTest groupSumTest =
                new PerformanceGroupSumTest("Test without multithreading", N_RUNS, new GroupSumStream(getGroups()));
        groupSumTest.run();
    }

    private static void startTest(ThreadsPoolGroupSum groupSum) {
        for (int nThreads : nThreads) {
            PerformanceGroupSumTest groupSumTest =
                    new PerformanceGroupSumTest("Test" + nThreads, N_RUNS, groupSum);
            groupSum.setnThreads(nThreads);
            groupSumTest.run();
        }
    }

    private static int[][] getGroups() {
        Random random = new Random();
        int[][] res = new int[N_GROUPS][N_NUMBERS_GROUP];

        for (int i = 0; i < N_GROUPS; i++) {
            for (int j = 0; j < N_NUMBERS_GROUP; j++) {
                res[i][j] = random.nextInt();
            }
        }
        return res;
    }

}
