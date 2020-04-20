package ArithmeticalCalculator;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static ArithmeticalCalculator.Calculator.*;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticExpressionsTests {

    @Test
    void testRemoveAll() {
        String str = "abcd%&77777777778t12g*";
        assertEquals("abcdtg", removeAllExceptLetters(str));

    }

    private String removeAllExceptLetters(String str) {
        return str.replaceAll("[^\\p{Alpha}]*", "");
    }

    @Test
    void testSplit() {
        String str = "abcd%&77777777778t12g*";
        String[] tokensExpectrd = {"abcd", "t", "g"};
        String[] tokensActual = str.split("[^\\p{Alpha}]+");
        System.out.println(Arrays.toString(tokensActual));
        assertArrayEquals(tokensExpectrd, tokensActual);
    }

    @Test
    void testCalculate() {
        assertEquals(10, calculate("2 *10 -5 * 2 / 3"));
        assertEquals(10, calculate("  2 *10 -5 * 2 / 3  "));
        assertEquals(Double.POSITIVE_INFINITY, calculate("2+5/0"));
        assertEquals(Double.NaN, calculate("*10 -5 *2 / 3"));
        assertEquals(Double.NaN, calculate("2*10 -5 * 2 # 3"));
    }

    @Test
    void testCalculateDouble() {
        assertEquals(30, calculate("2.5 *10 -5 / 2 * 3"));
        assertEquals(34.65, calculate(" 5.5 *5.5 -5.5 / 2.5 * 3.5 "));
        assertEquals(10, calculate("  2 *10 -5 * 2 / 3  "));
        assertEquals(Double.POSITIVE_INFINITY, calculate("2+5/0"));
        assertEquals(Double.NaN, calculate("*10 -5 *2 / 3"));
        assertEquals(Double.NaN, calculate("2*10 -5 * 2 # 3"));
    }

    @Test
    void testGetOperations() {
        String str = " 2 *10 -5 * 2 / 3";
        String[] operands = {"2", "10", "5", "2", "3"};
        String[] operations = {"", "*", "-", "*", "/"};
        assertArrayEquals(operands, getOperands(str));
        assertArrayEquals(operations, getOperations(str));
    }

    @Test
    void testCalculateOne() {
        assertEquals(10, calculateOne(20, "2", "/"));
        assertEquals(10, calculateOne(5, "2", "*"));
        assertEquals(Double.NaN, calculateOne(20, "2", "#"));
        assertEquals(Double.POSITIVE_INFINITY, calculateOne(20, "0", "/"));
    }

    @Test
    void testGetOperationsDouble() {
        String str = " 2.5 *10.10 -5 * 2.5 / 3";
        String[] operands = {"2.5", "10.10", "5", "2.5", "3"};
        assertArrayEquals(operands, getOperandsDouble(str));
    }

    @Test
    void testBrackets() {
        assertTrue(Calculator.checkBrackets("(5+5)"));
        assertTrue(Calculator.checkBrackets("((10-5)+(2+2))"));
        assertTrue(Calculator.checkBrackets("((5)+(5))"));

        assertFalse(Calculator.checkBrackets("(5*3)-2)-3)*2(/5"));
        assertFalse(Calculator.checkBrackets("(5-2)*2)"));
        assertFalse(Calculator.checkBrackets("(10/(2+2)"));
        assertFalse(Calculator.checkBrackets("(8*3)/2)"));
        assertFalse(Calculator.checkBrackets("((5*5)/(2*2)"));
    }
}
