package NumbersBox;

import java.util.*;

public class LinkedListNumbersBox extends ArrayListAndLinkedListNumberBox {

    @Override
    Collection<Integer> setCollection() {
        return new LinkedList<>();
    }
}
