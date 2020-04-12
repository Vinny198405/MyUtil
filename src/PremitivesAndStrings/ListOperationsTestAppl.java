package PremitivesAndStrings;

import Array.Array;
import IndexedLinkedList.IndexedLinkedList;
import PremitivesAndStrings.util.performance.IndexedListOperations;

public class ListOperationsTestAppl {
    private static IndexedListOperations arrayTest;
    private static IndexedListOperations linkedTest;

    public static void main(String[] args) {
        Integer nRuns = 1000;
        int nNumber = 10000;
        arrayTest = new IndexedListOperations("Array", nRuns,
                new Array<Integer>(), nNumber);
        linkedTest = new IndexedListOperations("LinkedList", nRuns,
                new IndexedLinkedList<Integer>(), nNumber);

        for (int i = 0; i <= 5; i++) {
            performanceTest(i * 20);
        }
    }

    private static void performanceTest(int probGet) {
        arrayTest.setProbGet(probGet);
        arrayTest.run();
        linkedTest.setProbGet(probGet);
        linkedTest.run();
    }
}

