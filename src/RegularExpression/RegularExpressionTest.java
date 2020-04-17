package RegularExpression;

import org.junit.jupiter.api.Test;


import static RegularExpression.RegularExpression.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegularExpressionTest {
    @Test
    void testVariableName() {
        //assertTrue tests
        assertTrue("$".matches(variableName()));
        assertTrue("a$2".matches(variableName()));
        assertTrue("A".matches(variableName()));
        assertTrue("aA_c12".matches(variableName()));
        assertTrue("__12345_t".matches(variableName()));
        //assertFalse tests
        assertFalse("1A_".matches(variableName()));
        assertFalse("ab _".matches(variableName()));
        assertFalse("av_*".matches(variableName()));
        assertFalse("_".matches(variableName()));
    }
    @Test
    void testLessThan256() {
        //assertTrue tests
        assertTrue("0".matches(numberLess256()));
        assertTrue("12".matches(numberLess256()));
        assertTrue("025".matches(numberLess256()));
        assertTrue("129".matches(numberLess256()));
        assertTrue("239".matches(numberLess256()));
        assertTrue("249".matches(numberLess256()));
        assertTrue("255".matches(numberLess256()));
        //assertFalse tests
        assertFalse("-1".matches(numberLess256()));
        assertFalse("256".matches(numberLess256()));
        assertFalse("1111".matches(numberLess256()));
        assertFalse("12#".matches(numberLess256()));
        assertFalse("1 ".matches(numberLess256()));
        assertFalse("2 7".matches(numberLess256()));
    }
    @Test
    void testIpV4() {
        //assertTrue tests
        assertTrue("0.0.0.0".matches(ipV4()));
        assertTrue("12.230.188.0".matches(ipV4()));
        assertTrue("0.255.0.255".matches(ipV4()));
        assertTrue("255.255.255.255".matches(ipV4()));
        //assertFalse tests
        assertFalse("280.1.2.3".matches(ipV4()));
        assertFalse("0.1.2".matches(ipV4()));
        assertFalse("*.1.2.3".matches(ipV4()));
        assertFalse("255.1.2.+3".matches(ipV4()));
    }
    @Test
    void phoneTest() {
        //assertTrue tests
        assertTrue("+972-50-1-22-33-44".matches(phone()));
        assertTrue("+972541223344".matches(phone()));
        assertTrue("057-1223344".matches(phone()));
        assertTrue("058-122-33-44".matches(phone()));
        //assertFalse tests
        assertFalse("057+1223344".matches(phone()));
        assertFalse("050-1-22-33-445".matches(phone()));
        assertFalse("50-1-22-33-44".matches(phone()));
        assertFalse("972-50-1-22-33-445".matches(phone()));
        assertFalse("+972-050-1-22-33-44".matches(phone()));
        assertFalse("050-1-22-33-4t5".matches(phone()));
        assertFalse("057-122—3344".matches(phone()));
        assertFalse("051-122-33-44".matches(phone()));
    }

    @Test
    void emailTest() {
        //assertTrue tests
        assertTrue("yura.granovsky@gmail.com".matches(email()));
        assertTrue("tt%2@mail.ru".matches(email()));
        assertTrue("tt_67@co.il.d-d.a-a".matches(email()));
        assertTrue("t5&4_s@ff.gt".matches(email()));
        //assertFalse tests
        assertFalse("yu ra@gmail.com".matches(email()));
        assertFalse("yu,ra@gmail.com".matches(email()));
        assertFalse("tt%2@ma_il.ru".matches(email()));
        assertFalse("tt_67@co.il.dd.aa.bb".matches(email()));
    }

    @Test
    void arithmeticExpressionTest() {
        //assertTrue tests
        assertTrue("2+3 /7".matches(arithmeticExpression()));
        assertTrue("2* 3;".matches(arithmeticExpression()));
        assertTrue("2".matches(arithmeticExpression()));
        assertTrue("2 + 3 / 7".matches(arithmeticExpression()));
        //assertFalse tests
        assertFalse("*3 /7".matches(arithmeticExpression()));
        assertFalse("2.5 +8/2  ".matches(arithmeticExpression()));
        assertFalse("2*5 +8#2 ".matches(arithmeticExpression()));
    }
}
