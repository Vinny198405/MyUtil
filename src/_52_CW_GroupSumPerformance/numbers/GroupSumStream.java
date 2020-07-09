package _52_CW_GroupSumPerformance.numbers;

import java.util.Arrays;

public class GroupSumStream extends GroupSum {

    public GroupSumStream(int[][] groups) {
        super(groups);
    }

    @Override
    public Long computeSum() {
        return Arrays.stream(groups)
                .flatMapToInt(Arrays::stream)
                .asLongStream()
                .parallel()
                .sum();
    }
}
