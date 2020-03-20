package Set;

import java.util.function.Predicate;

public interface Set<T> extends Iterable<T> {
    boolean add(T obj);

    Set<T> filter(Predicate<T> predicate);

    Object remove(Object pattern);

    boolean removeIf(Predicate<T> predicate);

    int size();

    boolean contains(T pattern);
}

