package Anagram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static Anagram.Anagram.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnagramTests {
    String word = "yellow";
    @BeforeEach
    void setUp() throws Exception{
    }

    @Test
    void testAnagramTrue(){
        assertTrue(isAnagram(word,"lowley"));
        assertTrue(isAnagram(word,"woleyl"));
    }
    @Test
    void testAnagramFalse(){
        assertFalse(isAnagram(word,""));
        assertFalse(isAnagram(word,"lowlyy"));
    }
}
