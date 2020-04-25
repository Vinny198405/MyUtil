package Exceptions;

public class RangeTestAppl {
    public static void main(String[] args) throws OutOfRangeMin, OutOfRangeMax {
        Range range = new Range(5, 20);
        range.checkNumber(3);
    }
}
