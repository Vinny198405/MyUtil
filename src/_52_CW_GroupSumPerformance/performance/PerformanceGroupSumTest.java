package _52_CW_GroupSumPerformance.performance;

import _52_CW_GroupSumPerformance.numbers.GroupSum;

public class PerformanceGroupSumTest extends PerformanceTests {

    private GroupSum groupSum;

    public PerformanceGroupSumTest(String testName, int nRuns, GroupSum groupSum) {
        super(testName, nRuns);
        this.groupSum = groupSum;
    }

    @Override
    protected void runTest() {
        groupSum.computeSum();
    }
}
