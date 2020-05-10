package Anagram;

import java.util.Arrays;

public class Anagram {
    public static boolean isAnagram(String word, String anagram) {
        if (word.length() != anagram.length()) {
            return false;
        }
        return sort(word).equals(sort(anagram));
    }

    public static String sort(String str) {
        char[] content = str.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }
}
