package NumbersBox;

import java.util.*;

public class ArrayListNumbersBox extends CollectionNumberBox {

    @Override
    Collection<Integer> setCollection() {
        return new ArrayList<>();
    }
}
