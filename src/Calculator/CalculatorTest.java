package Calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void testSum() {
        assertEquals(20, Calculator.sum(10, 10));
    }

    @Test
    void testDivide() {
        assertEquals(0, Calculator.devide(10, 0));
        assertEquals(0.5, Calculator.devide(1, 2), 0.1);
    }

    @Test
    void testmultiply() {
        assertEquals(100, Calculator.multiply(10, 10));
    }

    @Test
    void testMinus() {
        assertEquals(10, Calculator.Minus(20, 10));
    }

}

