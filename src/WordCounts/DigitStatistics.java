package WordCounts;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DigitStatistics {
    public static void main(String[] args) {
        int[] data = new Random().ints(1, Integer.MAX_VALUE)
                .distinct().limit(1000000).toArray();

        displayDigitStatistics(data);
    }

    private static void displayDigitStatistics(int[] data) {

        HashMap<Character, Integer> map = new HashMap<>();
        for (int num : data) {
            for (char c : ("" + num).toCharArray()) {
                map.merge(c, 1, Integer::sum);
            }
        }
        map.entrySet().stream().sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .forEach(System.out::println);
    }
}
