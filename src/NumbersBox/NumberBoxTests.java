package NumbersBox;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberBoxTests {
    private int[] numbers = {10, 20, 30, 30, 40, 40, 50, 60, 70, 80, 5, 15, 25};

    @Test
    void BoxArrayListTest() {
        int[] numbersExp = {10, 20, 30, 40, 50, 60, 70, 80, 5, 15, 25};
        NumbersBox numbersBox = new TreeSetNumbersBox();
        setUp(numbersBox);
        int initialSize = numbersBox.size();
        int removed = numbersBox.removeRepeated();
        assertEquals(numbersBox.size(), initialSize - removed);
        testListArray(numbersBox, numbersExp);
        assertEquals(3, numbersBox.removeNumbersInRange(30, 50));
        int[] numbersExp2 = {10, 20, 60, 70, 80, 5, 15, 25};
        testListArray(numbersBox, numbersExp2);
        numbersBox.removeNumber(10);
        numbersBox.removeNumber(20);
        int[] numbersExp3 = {60, 70, 80, 5, 15, 25};
        testListArray(numbersBox, numbersExp3);
    }

    @Test
    void testIterator() {
        int[] expectedNumbers = RandomArray(0, 10000, 1000);
        NumbersBox numbersBox = new HashSetNumbersBox();
        for (Integer num : expectedNumbers) {
            numbersBox.addNumber(num);
        }
        int[] actual = new int[numbersBox.size()];
        int j = 0;
        for (Iterator<Integer> itr = numbersBox.iterator(); itr.hasNext(); ) {
            actual[j++] = itr.next();
        }
        Arrays.sort(actual);
        assertArrayEquals(actual, expectedNumbers);
    }

    void setUp(NumbersBox box) {
        for (Integer num : numbers) {
            box.addNumber(num);
        }
    }

    private void testListArray(NumbersBox collection, int[] numbersExpected) {
        int[] numbersActual = new int[collection.size()];
        int ind = 0;
        for (Integer num : collection) {
            numbersActual[ind++] = num;
        }
        Arrays.sort(numbersActual);
        Arrays.sort(numbersExpected);
        assertArrayEquals(numbersExpected, numbersActual);
    }
    /**
     * function to create a randomized sorted array without repeating numbers
     *
     * @param start - minimal number
     * @param end   - maximum number
     * @param count - numbers in the array
     * @return the function return sorted random array
     */
    public static int[] RandomArray(int start, int end, int count) {
        Random rng = new Random();

        int[] result = new int[count];
        int cur = 0;
        int remaining = end - start;
        for (int i = start; i < end && count > 0; i++) {
            double probability = rng.nextDouble();
            if (probability < ((double) count) / (double) remaining) {
                count--;
                result[cur++] = i;
            }
            remaining--;
        }
        return result;
    }
}
