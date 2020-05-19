package StreamsIntroduction;

import java.util.Random;
import java.util.stream.IntStream;

public class StreamsFunctionality {
    public static void main(String[] args) {
        System.out.println("displayShufflingArray:");
        displayShufflingArray(new int[]{10, 15, 20, 25, 30, 35, 40, 45, 50});
        System.out.println("displaySportLotoNumbers:");
        displaySportLotoNumbers(1, 50, 10);
        System.out.println("displaySportLotoNumbers RuntimeException:");
        displaySportLotoNumbers(19, 20, 10);
    }

    public static void displaySportLotoNumbers(int min, int max, int nNumbers) {
        if (max - min + 1 < nNumbers)
            throw new RuntimeException("Does not meet the condition");

        new Random().ints(min, max).distinct().limit(nNumbers).forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    public static void displayShufflingArray(int[] ar) {
        new Random().ints(0, ar.length).distinct()
                .limit(ar.length).forEach(i -> System.out.print(ar[i] + " "));
        System.out.println();
    }
}
