package WordCounts;

import java.util.Comparator;
import java.util.Map.Entry;

public class StringCountsComparator implements Comparator<Entry<String, Integer>> {

    @Override
    public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
        int res = Integer.compare(o2.getValue(), o1.getValue());

        return res == 0 ? o1.getKey().compareTo(o2.getKey()) : res;
    }
}
