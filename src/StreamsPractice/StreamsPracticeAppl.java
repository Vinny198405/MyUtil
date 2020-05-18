package StreamsPractice;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsPracticeAppl {
    public static void main(String[] args) {
        displayAvgMinMaxSumArray(new int[]{1, 2, 3, 4, 5});
        displayAvgMinMaxSumList(Arrays.asList(1, 2, 3, 4, 5));
        int[] array = {1, 2, 9, 10, 20, 500, 700, 100000, 200000};
        displayNumbersGroupedByDigitsAmount(array);
        displayNumbersGroupedByOddEven(array);
        displayNumbersGroupedByCounting(array);
    }

    private static void displayNumbersGroupedByCounting(int[] array) {
        Map<String, Long> map = Arrays.stream(array).boxed().
                collect(Collectors.groupingBy(n -> n % 2 == 0 ? "even" :
                        "odd", Collectors.counting()));
        System.out.println(map);
    }

    private static void displayNumbersGroupedByOddEven(int[] array) {
        Map<String, List<Integer>> map = Arrays.stream(array).boxed().
                collect(Collectors.groupingBy(n -> n % 2 == 0 ? "even" : "odd"));
        System.out.println(map);
    }

    private static void displayNumbersGroupedByDigitsAmount(int[] array) {
        Map<Integer, List<Integer>> map = Arrays.stream(array).boxed().
                collect(Collectors.groupingBy(n -> Integer.toString(n).length()));
        System.out.println(map);
    }

    private static void displayAvgMinMaxSumList(List<Integer> list) {
        IntSummaryStatistics statistics = list.stream().mapToInt(x -> x).summaryStatistics();
        System.out.printf("avg = %f; min = %d; max = %d; sum = %d\n", statistics.getAverage(),
                statistics.getMin(), statistics.getMax(), statistics.getSum());

    }

    private static void displayAvgMinMaxSumArray(int[] array) {
        IntSummaryStatistics statistics = Arrays.stream((array)).summaryStatistics();
        System.out.printf("avg = %f; min = %d; max = %d; sum = %d\n", statistics.getAverage(),
                statistics.getMin(), statistics.getMax(), statistics.getSum());
    }
}
