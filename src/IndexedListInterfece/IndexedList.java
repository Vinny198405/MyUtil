package IndexedListInterfece;

import java.util.Comparator;
import java.util.function.Predicate;

public interface IndexedList<T> extends Iterable<T> {
    void add(T obj);

    boolean add(int ind, T obj);

    int binarySearch(T pattern);

    int binarySearch(T pattern, Comparator<T> comp);

    IndexedList<T> filter(Predicate<T> predicate);

    T get(int ind);

    int indexOf(Object pattern);

    int lastIndexOf(Object pattern);

    Object remove(int ind);

    Object remove(Object pattern);

    boolean removeIf(Predicate<T> predicate);

    Object set(int ind, T newObj);

    int size();

    void sort();

    void sort(Comparator<T> comp);
}
