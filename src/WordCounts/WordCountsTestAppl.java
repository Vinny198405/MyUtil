package WordCounts;

import java.util.Map.Entry;
import java.util.*;

public class WordCountsTestAppl {
    public static void main(String[] args) {
        String text = "lmn b abc lmn, lmn:abc - a ab ab";
        displayWOrdCounts(text);
    }

    private static void displayWOrdCounts(String text) {
        String words[] = getWords(text);
        System.out.println(Arrays.deepToString(words));
        HashMap<String, Integer> mapCounts = getMapCounts(words);
        List<Map.Entry<String, Integer>> listEntries = getListEntries(mapCounts);
        listEntries.sort(new StringCountsComparator());
        displayListEntries(listEntries);
    }

    private static void displayListEntries(List<Map.Entry<String, Integer>> listEntries) {
        for (Entry<String, Integer> entry : listEntries) {
            System.out.printf("%s -> %d \n", entry.getKey(),entry.getValue());
        }
    }

    private static List<Map.Entry<String, Integer>> getListEntries(HashMap<String, Integer> mapCounts) {
        return new ArrayList<>(mapCounts.entrySet());
    }

    private static HashMap<String, Integer> getMapCounts(String[] words) {
        HashMap<String, Integer> res = new HashMap<>();
        for (String word : words) {
            Integer count = res.getOrDefault(word, 0);
            res.put(word, count + 1);
        }
        return res;
    }

    private static String[] getWords(String text) {
        return text.split("\\W+");
    }

}
