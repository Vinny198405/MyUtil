package PremitivesAndStrings;

import Array.Array;
import IndexedLinkedList.IndexedLinkedList;

public class TestApp {
    public static void main(String[] args) {
        Integer nNum = 10000000;
        Array<Integer> array = new Array<Integer>();
        IndexedLinkedList<Integer> list = new IndexedLinkedList<Integer>();
        double startTime = System.currentTimeMillis();
        for (int i = 0; i < nNum; i++){
            array.add(100);
        }
        double endTime = System.currentTimeMillis();
        System.out.format("array: " + (endTime - startTime) + " ms" + "\n");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < nNum; i++){
            list.add(100);
        }
        endTime = System.currentTimeMillis();
        System.out.format("list: " + (endTime - startTime) + " ms");

    }
}
