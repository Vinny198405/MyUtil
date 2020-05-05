package NumbersBox;

import java.util.*;

public class ArrayListNumbersBox extends ArrayListAndLinkedListNumberBox {

    @Override
    Collection<Integer> setCollection() {
        return new ArrayList<>();
    }
}
