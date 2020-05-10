package Anagram;

import java.util.Arrays;

public class Anagram {
    public static boolean isAnagram(String word, String anagram) {
        if (word.length() != anagram.length()) {
            return false;
        }
        char [] tempWord = word.toCharArray();
        char [] tempAnagram = anagram.toCharArray();
        int summ = 0;
        for (int i = 0; i < tempWord.length; i++) {
            summ += tempWord[i] - tempAnagram[i];
        }
        return summ == 0;
    }
}
