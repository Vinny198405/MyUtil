package IndexedListTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.Iterator;

import IndexedLinkedList.IndexedLinkedList;
import IndexedListInterfece.IndexedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IndexedListTests {
    int numbers[] = {10, -8, 70, 75, 30};
    IndexedList<Integer> listNumbers;

    @BeforeEach
    void setUp() {
        listNumbers = getList();
    }

    @Test
    void testLinkedListRevers() {
        if (listNumbers instanceof IndexedLinkedList) {
            ((IndexedLinkedList<Integer>) listNumbers).reverse();
            int expected[] = {30, 75, 70, -8, 10};
            int actual[] = getActualNumbers(listNumbers);
            assertArrayEquals(expected, actual);
            listNumbers.add(40);
            ((IndexedLinkedList<Integer>) listNumbers).reverse();
            int expected1[] = {40, 10, -8, 70, 75, 30};
            int actual1[] = getActualNumbers(listNumbers);
            assertArrayEquals(expected1, actual1);
            listNumbers.remove(0);
            listNumbers.remove(0);
            listNumbers.remove(0);
            listNumbers.remove(0);
            ((IndexedLinkedList<Integer>) listNumbers).reverse();
            int expected2[] = {30, 75};
            int actual2[] = getActualNumbers(listNumbers);
            assertArrayEquals(expected2, actual2);
        }
    }

    @Test
    void testlastIndexOf() {
        assertEquals(-1, listNumbers.lastIndexOf(100));
        assertEquals(2, listNumbers.lastIndexOf(70));
        listNumbers.add(77);
        listNumbers.add(77);
        listNumbers.add(77);
        assertEquals(7, listNumbers.lastIndexOf(77));
    }

    @Test
    void testIndexOf() {
        assertEquals(-1, listNumbers.indexOf(100));
        assertEquals(2, listNumbers.indexOf(70));
        listNumbers.add(77);
        listNumbers.add(77);
        listNumbers.add(77);
        assertEquals(5, listNumbers.indexOf(77));
    }

    @Test
    void testAddGetSize() {
        for (int i = 0; i < listNumbers.size(); i++) {
            assertEquals(numbers[i], (int) listNumbers.get(i));
        }
        assertNull(listNumbers.get(listNumbers.size()));

    }

    private IndexedList<Integer> getList() {
        IndexedList<Integer> list = new IndexedLinkedList<Integer>();  // single place of updating

        for (int i = 0; i < numbers.length; i++) {
            list.add(numbers[i]);
        }
        return list;
    }

    @Test
    void testAddAtIndex() {
        //	{ 10, -8, 70, 75, 30 }
        int expectedNumbers[] = {-10, 10, -8, 70, -70, 75, 30, -30};
        assertTrue(listNumbers.add(0, -10));
        assertTrue(listNumbers.add(4, -70));
        assertTrue(listNumbers.add(7, -30));
        int actualNumbers[] = getActualNumbers(listNumbers);
        assertArrayEquals(expectedNumbers, actualNumbers);
        assertFalse(listNumbers.add(-1, 100));
        assertFalse(listNumbers.add(100, 100));

    }

    private int[] getActualNumbers(IndexedList<Integer> list) {
        int res[] = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) list.get(i);
        }
        return res;
    }

    @Test
    void testRemoveAtIndex() {
        int expectedNumbers[] = {-8, 75};
        assertEquals(10, listNumbers.remove(0));
        assertEquals(70, listNumbers.remove(1));
        assertEquals(30, listNumbers.remove(2));
        assertArrayEquals(expectedNumbers, getActualNumbers(listNumbers));
        assertNull(listNumbers.remove(2));
        assertNull(listNumbers.remove(-1));
    }

    @Test
    void testSetAtIndex() {
        int expectedNumbers[] = {-10, -8, -70, 75, -30};
        assertEquals(10, listNumbers.set(0, -10));
        assertEquals(70, listNumbers.set(2, -70));
        assertEquals(30, listNumbers.set(4, -30));
        assertArrayEquals(expectedNumbers, getActualNumbers(listNumbers));
        assertNull(listNumbers.set(-1, 100));
        assertNull(listNumbers.set(100, 100));
    }

    @Test
    void testSorting() {

        Person personMoshe = new Person(123, "Moshe", 1980);
        Person personVova = new Person(100, "Vova", 1970);

        try {
            @SuppressWarnings("unchecked")
            IndexedList<Person> listPersons = listNumbers.getClass() // getting object of class
                    .getConstructor() // getting constructor by default
                    .newInstance();
            listPersons.add(personMoshe);
            listPersons.add(personVova);
            listPersons.sort();
            assertEquals(personVova, listPersons.get(0));
            assertEquals(personMoshe, listPersons.get(1));
            listPersons.sort((a,b) -> a.getBirthYear() >= b.getBirthYear() ? -1 : 1);
            //listPersons.sort(new PersonAgeComparator());
            assertEquals(personVova, listPersons.get(1));
            assertEquals(personMoshe, listPersons.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testBinarySearch2() {
        String stringsNaturalOrder[] =
                {"abcd", "lm", "lmnopr", "x", "y", "z"};
        String stringsLengthOrder[] =
                {"x", "y", "z", "lm", "abcd", "lmnopr"};
        Comparator<String> compLength = IndexedListTests::compareStringLength;
       // Comparator<String> compLength = new StringLengthComparator();
        IndexedList<String> stringsNatural = getListStrings(stringsNaturalOrder);
        IndexedList<String> stringsLength = getListStrings(stringsLengthOrder);
        assertEquals(-3, stringsNatural.binarySearch("lmn"));
        assertEquals(1, stringsNatural.binarySearch("lm"));
        stringsNatural.remove("lm");
        assertEquals(-2, stringsNatural.binarySearch("lm"));
        assertEquals(-5, stringsLength.binarySearch("lmn", compLength));
        assertEquals(3, stringsLength.binarySearch("lm", compLength));
        stringsLength.set(3, "lmn");
        assertEquals(-4, stringsLength.binarySearch("lm", compLength));
    }

    public static int compareStringLength(String str0, String str1) {
        if (str0 == str1) {
            return 0;
        }
        return str0.length() >= str1.length() ? 1 : -1;
    }

    @Test
    void testBinarySearch() {
        String stringsNaturalOrder[] = {"abcd", "lm", "lmnopr", "x", "y", "z"};
        String stringsLengthOrder[] = {"x", "y", "z", "lm", "abcd", "lmnopr", "re"};
        Comparator<String> compLength = IndexedListTests::compareStringLength;
        //Comparator<String> compLength = new StringLengthComparator();
        IndexedList<String> stringsNatural = getListStrings(stringsNaturalOrder);
        IndexedList<String> stringsLength = getListStrings(stringsLengthOrder);
        assertEquals(-3, stringsNatural.binarySearch("lmn"));
        assertEquals(1, stringsNatural.binarySearch("lm"));
        assertEquals(-6, stringsLength.binarySearch("lmn", compLength));
        assertEquals(3, stringsLength.binarySearch("lm", compLength));
    }

    @SuppressWarnings("unchecked")
    private IndexedList<String> getListStrings(String[] strings) {
        try {
            // getting an object of the same class as the listNumbers
            /*******************************************/
            IndexedList<String> list = listNumbers
                    .getClass().getConstructor(int.class)
                    .newInstance(strings.length);
            /*******************************************/
            for (int i = 0; i < strings.length; i++) {
                list.add(strings[i]);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    void testFilter() {
        int expected[] = {10, -8, 70, 30};
        IndexedList<Integer> listNoEven = listNumbers.filter(IndexedListTests::test);
       // IndexedList<Integer> listNoEven = listNumbers.filter(new EvenNumbersPredicate());
        int actualNumbers[] = getActualNumbers(listNoEven);
        assertArrayEquals(expected, actualNumbers);
    }

    public static boolean test(Integer num) {
        return num % 2 == 0;
    }

    @Test
    void testRemoveIf() {
        // {10, -8, 70, 75, 30};
        listNumbers.add(75);
        int expected[] = {75, 75};
//        EvenNumbersPredicate predicateEven = new EvenNumbersPredicate();
//        assertTrue(listNumbers.removeIf(predicateEven));
//        assertFalse(listNumbers.removeIf(predicateEven));
        assertTrue(listNumbers.removeIf(IndexedListTests::test));
        assertFalse(listNumbers.removeIf(IndexedListTests::test));
        assertArrayEquals(expected, getActualNumbers(listNumbers));
    }

    @Test
    /**
     * additional test for sorting array numbers according to the following all odd
     * numbers should go before the even ones odd numbers should be sorted in the
     * ascending order even numbers should be sorted in the descending order
     */
    void testSortingEvenOdd() {
        // {10, -8, 70, 75, 30}
        listNumbers.add(73);
        listNumbers.add(3);
        int[] expected = {3, 73, 75, 70, 30, 10, -8};
       // listNumbers.sort(new EvenOddComparator());
        listNumbers.sort((n1,n2) -> {
            if (n1 % 2 == 1 && n2 % 2 == 0) {
                return -1;
            }
            if (n1 % 2 == 0 && n2 % 2 == 1) {
                return 1;
            }
            if (n1 % 2 == 1 && n2 % 2 == 1) {
                return n1 - n2;
            }
            return n2 - n1;
        });
        assertArrayEquals(expected, getActualNumbers(listNumbers));
    }

    @Test
    void testRemoveObject() {
        // {10, -8, 70, 75, 30};
        int expected[] = {10, -8, 75, 30};
        listNumbers.remove((Integer) 70);
        assertArrayEquals(expected, getActualNumbers(listNumbers));
    }

    @Test
    void testInterableSum() {
        int sumExpected = 177;
        int sumActual = 0;
        for (Integer num : listNumbers) {
            sumActual += num;
        }
        assertEquals(sumActual, sumExpected);
    }

    @Test
    void testIterableRemove() {
        listNumbers.add(77);
        listNumbers.add(78);
        listNumbers.add(80);
        int expected[] = {-8, 75, 77};
        // {10, -8, 70, 75, 30,77,78,80};
        // {10,70,30,78}
        // wrong iterating and removing!!!
//		for(Integer num: list) {
//			if (num >= 0 && num %2 == 0) {
//				list.remove(num);
//			}
//		}
        Iterator<Integer> it = listNumbers.iterator();
        while (it.hasNext()) {
            Integer num = it.next();
            if (num >= 0 && num % 2 == 0) {
                it.remove();
            }
        }
        assertArrayEquals(expected, getActualNumbers(listNumbers));
    }

}

