package ArithmeticalCalculator;

import java.util.HashMap;
import java.util.function.BinaryOperator;

public class Calculator {
    static HashMap<String, BinaryOperator<Double>> mapOperation;

    static {
        mapOperation = new HashMap<>();
        mapOperation.put("+", Double::sum);
        mapOperation.put("-", (a, b) -> a - b);
        mapOperation.put("*", (a, b) -> a * b);
        mapOperation.put("/", (a, b) -> b == 0 ? Double.POSITIVE_INFINITY : a / b);
    }

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
        try {
            double op2Number = Double.parseDouble(op2);
            return mapOperation.getOrDefault(operation, (a, b) -> Double.NaN).apply(op1, op2Number);
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    public static String[] getOperands(String expr) {
        return expr.trim().split("\\D+");
    }

    public static String[] getOperations(String expr) {
        String tmp = expr.replaceAll(" ", "");
        return tmp.split("[\\d.]+");
    }

    public static String[] getOperandsDouble(String expr) {
        return expr.trim().split("[-+/* ]+");
    }

    public static boolean checkBrackets(String expr) {
        int count = 0;
        for (char c : expr.toCharArray()) {
            if (c == '(') count++;
            if (c == ')' && --count < 0) break;
        }
        return count == 0;
    }
}
