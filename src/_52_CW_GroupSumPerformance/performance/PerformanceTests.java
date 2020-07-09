package _52_CW_GroupSumPerformance.performance;

public abstract class PerformanceTests {
    private String testName;
    protected int nRuns;

    PerformanceTests(String testName, int nRuns) {
        this.testName = testName;
        this.nRuns = nRuns;
    }

    protected abstract void runTest();

    public void run() {
        double startTime = System.currentTimeMillis();

        for (int i = 0; i < nRuns; i++) {
            runTest();
        }

        double endTime = System.currentTimeMillis();

        System.out.format("%s completed %d times in %f ms \n", testName, nRuns, endTime - startTime);
    }
}