package CollectionViews;

import java.util.*;

public class CollectionViewsAppl {
    public static void main(String[] args) {
        Integer number[] = {100, 20, -10, 200, 50, 12, 38, 70};
        // List<Integer> listNumbers = Arrays.asList(number);
        List<Integer> listNumbers =
                new ArrayList<>(Arrays.asList(number));
        List<Integer> subList = listNumbers.subList(2, 5);
        displayCollection(subList);
        //what is it view
        subList.remove((Integer)(-10));
        System.out.println("Original collection");
        subList.add(0,10);
        displayCollection(listNumbers);
        TreeSet<Integer> treeNumbers = new TreeSet<>(Arrays.asList(number));
        System.out.println("Original tree");
        displayCollection(treeNumbers);
        //removing numbers from 30 to 80
        TreeSet<Integer> tree30_80 = (TreeSet<Integer>) treeNumbers.subSet(30,true,80,true);
        tree30_80.clear();
        System.out.println("Original tree with out from 30 to 80");
        displayCollection(treeNumbers);
    }

    private static void displayCollection(Collection<Integer> collection) {
        for (Integer num : collection) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
