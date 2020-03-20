package Array2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArrayTests {
    int numbers[] = {10, -8, 70, 75, 30};
    int numbers1[] = {10, 40, 70, 60, 30, 50, 90, 80, 20};
    int numbers2[] = {10, 20, 30, 40, 50, 60, 70, 80, 90};

    private Array getArray(int ar[]) {
        Array array = new Array(ar.length);

        for (int i = 0; i < ar.length; i++) {
            array.add(ar[i]);
        }
        return array;
    }

    @Test
    void testSort() {
        Array array = getArray(numbers1);
        array.sort();
        int expectedNumbers[] = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        int actualNumbers[] = getActualNumbers(array);
        assertArrayEquals(expectedNumbers, actualNumbers);
    }

    @Test
    void testbinarySearch() {
        Array array = getArray(numbers2);
        assertEquals(3, array.binarySearch(40));
        assertEquals(0, array.binarySearch(10));
        assertEquals(8, array.binarySearch(90));
        assertEquals(-10, array.binarySearch(100));
        assertEquals(-1, array.binarySearch(5));
        assertEquals(-5, array.binarySearch(45));
    }

    @Test
    void testSortComp() {
        Array array = getArray(numbers1);
        array.sort(new NewObjactComparator());
        int expectedNumbers[] = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        int actualNumbers[] = getActualNumbers(array);
        assertArrayEquals(expectedNumbers, actualNumbers);
    }

    @Test
    void testAddGetSize() {
        Array array = getArray(numbers);
        for (int i = 0; i < array.size(); i++) {
            assertEquals(numbers[i], array.get(i));
        }
        assertNull(array.get(array.size()));

    }

    @Test
    void testAdd() {
        Array arr = getArray(numbers);

        assertFalse(arr.add(10, 66));
        assertTrue(arr.add(3, 66));
        int nn[] = {10, -8, 70, 66, 75, 30};
        int tmp[] = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            tmp[i] = (int) arr.get(i);
        }
        assertArrayEquals(tmp, nn);

    }

    @Test
    void testRemove1() {
        Array ar = new Array(1);
        ar.add(0, 10);
        assertEquals(10, ar.remove(0));
    }


    @Test
    void testRemove() {
        Array arr = getArray(numbers);
        assertEquals(numbers[3], arr.remove(3));

        int nn[] = {10, -8, 70, 30};
        int tmp[] = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            tmp[i] = (int) arr.get(i);
        }
        assertArrayEquals(tmp, nn);
    }

    @Test
    void testSet() {
        Array arr = getArray(numbers);
        assertEquals(numbers[3], arr.set(3, 55));
    }

    @Test
    void testAddAtIndex() {
        int expectedNumbers[] = {-10, 10, -8, 70, -70, 75, 30, -30};
        Array array = getArray(numbers);
        assertTrue(array.add(0, -10));
        assertTrue(array.add(4, -70));
        assertTrue(array.add(7, -30));
        int actualNumbers[] = getActualNumbers(array);
        assertArrayEquals(expectedNumbers, actualNumbers);
        assertFalse(array.add(-1, 100));
        assertFalse(array.add(100, 100));

    }

    private int[] getActualNumbers(Array array) {
        int res[] = new int[array.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) array.get(i);
        }
        return res;
    }

    @Test
    void testRemoveAtIndex() {
        int expectedNumbers[] = {-8, 75};
        Array array = getArray(numbers);
        assertEquals(10, array.remove(0));
        assertEquals(70, array.remove(1));
        assertEquals(30, array.remove(2));
        assertArrayEquals(expectedNumbers, getActualNumbers(array));
        assertNull(array.remove(2));
        assertNull(array.remove(-1));
    }

    @Test
    void testSetAtIndex() {
        int expectedNumbers[] = {-10, -8, -70, 75, -30};
        Array array = getArray(numbers);
        assertEquals(10, array.set(0, -10));
        assertEquals(70, array.set(2, -70));
        assertEquals(30, array.set(4, -30));
        assertArrayEquals(expectedNumbers, getActualNumbers(array));
        assertNull(array.set(-1, 100));
        assertNull(array.set(100, 100));
    }

}


