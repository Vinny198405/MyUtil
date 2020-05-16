package StreamsIntroduction;

import org.junit.jupiter.api.MethodOrderer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamsTestAppl {
    public static void main(String[] args) {
        int N = 10;
        // finding sum of N even random numbers
        int sum = new Random().ints(100, 1000).
                filter(n -> n % 2 == 0).limit(N).sum();
        System.out.println(sum);
        // create List of N odd numbers and display as list.toString()
        List<Integer> list = new Random().ints(100, 1000).
                filter(n -> n % 2 != 0).limit(N).boxed().collect(Collectors.toList());
        System.out.println(list);
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(10, 20);
        List<Integer> list3 = Arrays.asList(30, 40, 100);
        List[] arLists = {list1, list2, list3};
        int sum1 = Arrays.stream(arLists).flatMap(l -> l.stream()).
                mapToInt(x -> (int) x).sum();
        System.out.println(sum1);

    }
}
