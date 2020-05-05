package NumbersBox;

import java.util.*;

public abstract class CollectionNumberBox implements NumbersBox {
    Collection<Integer> collection;

    abstract Collection<Integer> setCollection();

    public CollectionNumberBox() {
        this.collection = setCollection();
    }

    @Override
    public void addNumber(int number) {
        collection.add(number);
    }

    @Override
    public void removeNumber(int number) {
        collection.remove(number);
    }

    @Override
    public int removeNumbersInRange(int from, int to) {
        Collection<Integer> newColl = setCollection();
        int sizeOld = size();
        for (Integer num : collection) {
            if (num < from || num > to) {
                newColl.add(num);
            }

        }
        collection = newColl;
        return sizeOld - newColl.size();
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public Iterator<Integer> iterator() {
        return collection.iterator();
    }
}
