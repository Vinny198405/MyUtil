package Calculator;

public class Calculator {
    public static void main(String[] args) {
        System.err.println("Sum = " + sum(4, 3));
        System.err.println("Devide = " + devide(20, 10));
        System.err.println("Minus = " + Minus(4, 3));
        System.err.println("Multiply = " + multiply(4, 3));

    }

    public static int sum(int a, int b) {
        int res = a + b;
        return res;
    }

    static public double devide(int a, int b) {
        double res1 = a, res2 = b;

        return (a == 0 || b == 0) ? 0 : (res1 / res2);
    }

    public static int Minus(int a, int b) {

        return a - b;
    }

    public static int multiply(int a, int b) {

        return a * b;
    }
}

