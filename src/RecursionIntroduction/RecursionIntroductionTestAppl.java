package RecursionIntroduction;

public class RecursionIntroductionTestAppl {
    public static void main(String[] args) {
         System.out.println(factorial(5));
         System.out.println(pow(5, 3));
        int ar[] = {1, 1, 1, 1, 1};
         System.out.println(sum(ar));
        System.out.println(powHW(5, 2));
        System.out.println(square(5));
    }

    //	write method long pow(int, int)
    //	1. No cycles; 2. Just + - operators
    private static int powHW(int a, int b) {
        if (b == 0) return 1;
        return sumPow(a, powHW(a, b - 1));
    }

    private static int sumPow(int a, int count) {
        if (count == 0) return 0;
        return a + sumPow(a, count - 1);
    }

    //	2. write long square(int x)
    //	1. No cycles, 2. just + - operators, 3. No additional functions

    public static int square(int x) {
        if (x == 1) return 1;
        return x + x - 1 + square(x - 1);
    }

    // *****************  CW Recursion ****************
    private static int pow(int a, int b) {
        if (b == 0) {
            return 1;
        }
        return a * pow(a, b - 1);
    }

    private static long factorial(int a) {
        if (a == 0) {
            return 1;
        }
        return a * factorial(a - 1);
    }

    static int sum(int[] ar) {
        return sum(0, ar);
    }

    private static int sum(int i, int[] ar) {
        if (i == ar.length) {
            return 0;
        }
        return ar[i] + sum(i + 1, ar);
    }

}
