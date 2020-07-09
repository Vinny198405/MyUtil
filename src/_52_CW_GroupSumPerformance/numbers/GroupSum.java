package _52_CW_GroupSumPerformance.numbers;

public abstract class GroupSum {
    protected int[][] groups;

    public GroupSum(int[][] groups) {
        this.groups = groups;
    }
    public abstract Long computeSum();
}
