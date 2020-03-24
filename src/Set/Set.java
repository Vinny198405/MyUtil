package Set;

import java.util.Iterator;
import java.util.function.Predicate;

public interface Set<T> extends Iterable<T> {
    boolean add(T obj);

    Set<T> filter(Predicate<T> predicate);

    Object remove(Object pattern);

   default boolean removeIf(Predicate<T> predicate){
        Iterator<T> itr = iterator();
        int initSize = size();
        while (itr.hasNext()) {
            T obj = itr.next();
            if (predicate.test(obj)) {
                itr.remove();
            }
        }
        return initSize > size();
    }

    int size();

    boolean contains(T pattern);
}

