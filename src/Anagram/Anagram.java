package Anagram;

import java.util.HashMap;

public class Anagram {
    public static boolean isAnagram(String word, String anagram) {
        if (word == null || anagram == null) {
            return false;
        }

        if(word.length() != anagram.length()) {
            return false;
        }
        HashMap<Character, Integer> charCountsMap = getCharCounts(word);
        for(char c: anagram.toCharArray()) {
            if (charCountsMap.merge(c, 1, (w, a) -> w - a) < 0) {
                return false;
            }
        }
        return true;
    }

    private static HashMap<Character, Integer> getCharCounts(String word) {
        HashMap<Character, Integer> res = new HashMap<>();
        for(char c: word.toCharArray()) {
            Integer count = res.getOrDefault(c, 0);
            res.put(c, count + 1);
        }
        return res;
    }
}
