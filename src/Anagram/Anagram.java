package Anagram;

import java.util.Arrays;

public class Anagram {
    public static boolean isAnagram(String word, String anagram) {
        if (word == null || anagram == null) {
            return false;
        }

        if (word.length() != anagram.length()) {
            return false;
        }

        if (word.equals(anagram)) {
            return true;
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
