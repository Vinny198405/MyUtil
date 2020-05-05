package NumbersBox;

import java.util.*;

public class HashSetNumbersBox extends CollectionNumberBox {

    @Override
    Collection<Integer> setCollection() {
        return new HashSet<>();
    }

    @Override
    public int removeRepeated() {
        return 0;
    }
}
