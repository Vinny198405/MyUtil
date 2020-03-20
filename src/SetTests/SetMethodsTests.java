package SetTests;

import Set.SetMethods;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;

public class SetMethodsTests {

    /* tests for HW #3 */
    @Test
    void testUnion() {
        int ar1[] = {10, 30, -8, 20};
        int ar2[] = {0, -3, 7, 11};
        int ar3[] = {0, -8, 20, 10};
        int exp1[] = {10, 30, -8, 20, 0, -3, 7, 11};
        int exp2[] = {10, 30, -8, 20, 0};
        assertArrayEquals(exp1, SetMethods.union(ar1, ar2));
        assertArrayEquals(exp2, SetMethods.union(ar1, ar3));
    }

    @Test
    void testIntersection() {
        int ar1[] = {10, 30, -8, 20};
        int ar2[] = {0, -3, 7, 11};
        int ar3[] = {0, -8, 20, 10};
        int exp1[] = {};
        int exp2[] = {10, -8, 20};
        assertArrayEquals(exp1, SetMethods.intersection(ar1, ar2));
        assertArrayEquals(exp2, SetMethods.intersection(ar1, ar3));
    }

    @Test
    void testDifference() {
        int ar1[] = {10, 30, -8, 20};
        int ar2[] = {0, -3, 7, 11};
        int ar3[] = {0, -8, 20, 10};
        int exp1[] = ar1;
        int exp2[] = {30};
        assertArrayEquals(exp1, SetMethods.difference(ar1, ar2));
        assertArrayEquals(exp2, SetMethods.difference(ar1, ar3));
    }
}

