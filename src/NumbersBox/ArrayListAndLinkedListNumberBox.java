package NumbersBox;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class ArrayListAndLinkedListNumberBox extends CollectionNumberBox {

    @Override
    public int removeRepeated() {
        int count = collection.size();
        Set<Integer> set = new LinkedHashSet<>(collection);
        collection.clear();
        collection.addAll(set);
        return count - collection.size();
    }
}
