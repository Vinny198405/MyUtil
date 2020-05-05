package NumbersBox;

import java.util.Collection;
import java.util.TreeSet;

public class TreeSetNumbersBox extends CollectionNumberBox{

    @Override
    Collection<Integer> setCollection() {
        return new TreeSet<>();
    }

    @Override
    public int removeRepeated() {
        return 0;
    }
}
