package NumbersBox;

import java.util.*;

public class LinkedListNumbersBox extends CollectionNumberBox {

    @Override
    Collection<Integer> setCollection() {
        return new LinkedList<>();
    }
}
