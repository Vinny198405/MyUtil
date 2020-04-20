package ArithmeticalCalculator;

public class Calculator {
    static public double calculate(String expr) {
        String[] operands = getOperandsDouble(expr);
        String[] operations = getOperations(expr);
        if (operands.length != operations.length) {
            return Double.NaN;
        }
        double res = Double.parseDouble(operands[0]);
        for (int i = 1; i < operands.length; i++) {
            res = calculateOne(res, operands[i], operations[i]);
        }
        return res;
    }

    public static Double calculateOne(double op1, String op2, String operation) {
        double op2Number = Double.parseDouble(op2);
        switch (operation) {
            case "+":
                return op1 + op2Number;
            case "*":
                return op1 * op2Number;
            case "-":
                return op1 - op2Number;
            case "/":
                return op2Number == 0 ? Double.POSITIVE_INFINITY : op1 / op2Number;
        }
        return Double.NaN;
    }

    public static String[] getOperands(String expr) {
        return expr.trim().split("\\D+");
    }

    public static String[] getOperations(String expr) {
        return expr.split("[\\d. ]+");
    }

    public static String[] getOperandsDouble(String expr) {
        return expr.trim().split("[-+/* ]+");
    }

    public static Boolean checkBrackets(String expr) {
        String brackets = expr.replaceAll("[^()]", "");
        String bracketsTemp = "";
        while (true) {
            bracketsTemp = brackets.replaceAll("\\(\\)", "");
            if (brackets.equals(bracketsTemp)) {
                break;
            }
            brackets = bracketsTemp;
        }

        return brackets.length() == 0;
    }
}
