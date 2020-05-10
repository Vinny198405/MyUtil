package Anagram;

import java.util.Arrays;

public class Anagram {
    public static boolean isAnagram(String word, String anagram) {
        if (word.length() != anagram.length()) {
            return false;
        }
        char [] tempSource = word.toCharArray();
        char [] tempRevers = anagram.toCharArray();
        int summ = 0;
        for (int i = 0; i < tempSource.length; i++) {
            summ += tempSource[i] - tempRevers[i];
        }
        return summ == 0;
    }
}
