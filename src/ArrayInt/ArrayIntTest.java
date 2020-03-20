package ArrayInt;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ArrayIntTest {


    @Test
    void testTestSearch() {
        int array[] = {10, -20, 12};
        assertEquals(1, ArrayInt.search(array, -20));
        assertTrue(ArrayInt.search(array, -200) < 0);
    }

    @Test
    void testTestSort() {
        int nNumbers = 1000;
        int array[] = getRandomArray(nNumbers);
        ArrayInt.sort(array);
        for (int i = 1; i < nNumbers; i++) {
            assertTrue(array[i - 1] <= array[i]);
        }
    }

    @Test
        // home work
    void testArray() {
        int s[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int d[] = {15, 25, 35, 45, 55, 65, 75, 85, 95, 105};
        System.arraycopy(s, 3, d, 5, 4);
        assertArrayEquals(new int[]{15, 25, 35, 45, 55, 40, 50, 60, 70, 105}, d);
    }

    @Test
        // home work
    void arrayBinarySearch() {
        int nNumbers = 1000;
        int expectedIndex = 20;
        int a[] = getRandomArray(nNumbers);
        Arrays.sort(a);
        int key = a[expectedIndex];
        assertEquals(expectedIndex, Arrays.binarySearch(a, key));
        assertEquals(-1, Arrays.binarySearch(a, -1));
        int ar[] = {10, 20, 20, 40, 100, 200, 300};
        assertEquals(3, Arrays.binarySearch(ar, 40));
        //assertEquals(1,Arrays.binarySearch(ar, 20));
        int ind = Arrays.binarySearch(ar, 20);
        assertEquals(20, ar[ind]);
        assertEquals(-4, Arrays.binarySearch(ar, 25));
        assertEquals(-2, Arrays.binarySearch(ar, 15));

    }

    @Test
    void testBinarySearch() {
        int ar[] = {10, 20, 20, 30, 30, 40, 50, 60, 70, 70, 70, 200, 300, 400};
        assertEquals(0, ArrayInt.binarySearch(ar, 10));
        assertEquals(6, ArrayInt.binarySearch(ar, 50));
        assertEquals(-2, ArrayInt.binarySearch(ar, 15));
        assertEquals(1, ArrayInt.binarySearch(ar, 20));
        assertEquals(3, ArrayInt.binarySearch(ar, 30));
        assertEquals(3, ArrayInt.countSorted(ar, 70));
    }

    int[] getRandomArray(int nNumbers) {
        int array[] = new int[nNumbers];
        for (int i = 0; i < nNumbers; i++) {
            array[i] = (int) (Math.random() * 1000000);
        }
        return array;
    }

    @Test
    void restInsertNumber() {
        int ar[] = {1, 2, -5, 10, 8};
        int exp0[] = {100, 1, 2, -5, 10, 8};
        int exp6[] = {1, 2, -5, 10, 8, 50};
        int exp3[] = {1, 2, -5, 30, 10, 8};
        assertArrayEquals(exp0, ArrayInt.insert(ar, 0, 100));
        assertArrayEquals(exp6, ArrayInt.insert(ar, 5, 50));
        assertArrayEquals(exp3, ArrayInt.insert(ar, 3, 30));
        assertArrayEquals(ar, ArrayInt.insert(ar, -5, 50));

    }

    @Test
    void restInsertNumber1() {
        int ar[] = {1, 2, -5, 10, 8};
        int exp0[] = {100, 1, 2, -5, 10, 8};
        int exp6[] = {1, 2, -5, 10, 8, 50};
        int exp3[] = {1, 2, -5, 30, 10, 8};
        assertArrayEquals(exp0, ArrayInt.insert1(ar, 0, 100));
        assertArrayEquals(exp6, ArrayInt.insert1(ar, 5, 50));
        assertArrayEquals(exp3, ArrayInt.insert1(ar, 3, 30));
        assertArrayEquals(ar, ArrayInt.insert1(ar, -5, 50));

    }

    @Test
    void testRemuveNumber() {
        int ar[] = {1, 2, -5, 10, 8};
        int exp0[] = {2, -5, 10, 8};
        int exp4[] = {1, 2, -5, 10};
        int exp3[] = {1, 2, -5, 8};
        assertArrayEquals(exp0, ArrayInt.remove(ar, 0));
        assertArrayEquals(exp4, ArrayInt.remove(ar, 4));
        assertArrayEquals(exp3, ArrayInt.remove(ar, 3));
        assertArrayEquals(ar, ArrayInt.remove(ar, -1));
        assertArrayEquals(ar, ArrayInt.remove(ar, ar.length));
    }

    @Test
    void testInsertSorted() {
        int ar[] = {10, 20, 30, 40, 50};
        int exp0[] = {5, 10, 20, 30, 40, 50};
        int exp5[] = {10, 20, 30, 40, 50, 55};
        int exp3[] = {10, 20, 30, 35, 40, 50};
        assertArrayEquals(exp0, ArrayInt.insertSorted(ar, 5));
        assertArrayEquals(exp5, ArrayInt.insertSorted(ar, 55));
        assertArrayEquals(exp3, ArrayInt.insertSorted(ar, 35));
    }

    /* tests for HW #3 */
    @Test
    void testUnion() {
        int ar1[] = {10, 30, -8, 20};
        int ar2[] = {0, -3, 7, 11};
        int ar3[] = {0, -8, 20, 10};
        int exp1[] = {10, 30, -8, 20, 0, -3, 7, 11};
        int exp2[] = {10, 30, -8, 20, 0};
        assertArrayEquals(exp1, ArrayInt.union(ar1, ar2));
        assertArrayEquals(exp2, ArrayInt.union(ar1, ar3));
    }

    @Test
    void testIntersection() {
        int ar1[] = {10, 30, -8, 20};
        int ar2[] = {0, -3, 7, 11};
        int ar3[] = {0, -8, 20, 10};
        int exp1[] = {};
        int exp2[] = {10, -8, 20};
        assertArrayEquals(exp1, ArrayInt.intersection(ar1, ar2));
        assertArrayEquals(exp2, ArrayInt.intersection(ar1, ar3));
    }

    @Test
    void testDifference() {
        int ar1[] = {10, 30, -8, 20};
        int ar2[] = {0, -3, 7, 11};
        int ar3[] = {0, -8, 20, 10};
        int exp1[] = ar1;
        int exp2[] = {30};
        assertArrayEquals(exp1, ArrayInt.difference(ar1, ar2));
        assertArrayEquals(exp2, ArrayInt.difference(ar1, ar3));
    }


}

