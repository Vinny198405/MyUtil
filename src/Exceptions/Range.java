package Exceptions;

public class Range {
    int min;
    int max;
    public Range(int min, int max) {
        if (max <= min) {
            throw new IllegalArgumentException("max can't be less than min");

        }
        this.min = min;
        this.max = max;
    }
    public void checkNumber(int number) throws OutOfRangeMin,
            OutOfRangeMax {
        if (number < min) {
            throw new OutOfRangeMin("" + (number - min));
        }
        if (number > max) {
            throw new OutOfRangeMax("" + (number - max));
        }
    }
}
