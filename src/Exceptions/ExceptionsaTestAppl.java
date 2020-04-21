package Exceptions;

public class ExceptionsaTestAppl {

    public static void main(String[] args) {
        String strNumber = "12.78";
        System.out.printf("strNumber: %s is number, it is %s", strNumber, isNumber(strNumber));
    }

    private static Object isNumber(String strNumber) {
        boolean res = false;
        try {
            Double.parseDouble(strNumber);
            res = true;
            return true;
        } catch (NumberFormatException e) {
            res = false;
           return false;
        }
        finally {
            System.out.printf("returned %s\n", res);
        }
    }
}
