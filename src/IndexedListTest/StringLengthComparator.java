package IndexedListTest;

import java.util.Comparator;

public class StringLengthComparator implements Comparator<String> {

    @Override
    public int compare(String str0, String str1) {
        if (str0 == str1) {
            return 0;
        }
        return str0.length() >= str1.length() ? 1 : -1;
    }

}
