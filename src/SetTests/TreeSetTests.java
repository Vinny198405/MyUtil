package SetTests;

import Set.Set;
import Set.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TreeSetTests {
    Integer numbers[] = {10, 20, 11, -8, 7, 13, 9, 100, 2, 70, 15, 21, 121, 500};
    Set<Integer> set;

    @BeforeEach
    void setUp() {
        set = new TreeSet<Integer>();
        for (Integer num : numbers) {
            set.add(num);
        }
    }

    @Test
    void testAddContains() {
        for (Integer num : numbers) {
            assertTrue(set.contains(num));
        }
        assertFalse(set.contains(8));
        assertFalse(set.add(-8));
        assertTrue(set.add(8));
    }

    @Test
    void testIterator() {
        testSetArray(set, numbers);
    }

    private void testSetArray(Set<Integer> setTest, Integer[] numbersExpected) {
        Integer[] numbersActual = new Integer[setTest.size()];
        int ind = 0;
        for (Integer num : setTest) {
            numbersActual[ind++] = num;

        }
        Arrays.sort(numbersExpected);
        Arrays.sort(numbersActual);
        assertArrayEquals(numbersExpected, numbersActual);

    }

    @Test
    void testFilter() {
        Set<Integer> evenSet = set.filter(new EvenNumbersPredicate());
        Integer[] evenNumbers = {10, 20, -8, 100, 2, 70, 500};
        testSetArray(evenSet, evenNumbers);
    }

    @Test
    void testRemoveObj() {
        Integer[] numbersNo100 = { 10, 20, 11, 7, 13, 9, 2, 70, 15, 21, 121 };
        assertEquals(100, set.remove(100));
        assertNull(set.remove(100));
        assertEquals(500, set.remove(500));
        assertEquals(-8, set.remove(-8));
        testSetArray(set, numbersNo100);
    }

    @Test
    void remove() {
        assertTrue(set.add(8));
        assertTrue(set.contains(8));
        assertEquals(8, set.remove(8));
        assertFalse(set.contains(8));
        assertEquals(null, set.remove(-50));
    }

    @Test
    void testRemoveIf() {
        Integer[] oddNumbers = {11, 7, 13, 9, 15, 21, 121};
        assertTrue(set.removeIf(new EvenNumbersPredicate()));
        testSetArray(set, oddNumbers);
    }

    @Test
    void testRemIf() {
        for (int i = 0; i < 10000; i++) {
            set.add((int) (Math.random() * Integer.MAX_VALUE));
        }
        assertTrue(set.removeIf(new EvenNumbersPredicate()));
        for (int num : set) {
            assertTrue(num % 2 == 1);
        }
    }

    @Test
    void testRemoveAll() {
        set.removeIf(new TruePredicate());
        assertEquals(0, set.size());
    }

    @Test
    void iterator() {
        int expectedNumbers[] = RandomArray(0, 10000, 1000);
        set = new TreeSet<Integer>();
        for (Integer num : expectedNumbers) {
            set.add(num);
        }
        int[] actual = new int[set.size()];
        int j = 0;
        for (Iterator<Integer> itr = set.iterator(); itr.hasNext(); ) {
            actual[j++] = itr.next();
        }
        Arrays.sort(actual);
        assertArrayEquals(actual, expectedNumbers);
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


