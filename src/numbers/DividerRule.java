package numbers;

import Exceptions.RangeException;
import Exceptions.RuleException;

public class DividerRule implements Rule {
    private int divider;

    public DividerRule(int divider) {
        this.divider = divider;
    }

    @Override
    public void checkRule(int number, int min, int max) throws RuleException {
        min = setMin(min);
        max = setMax(max);

        if (min > max) {
            throw new RangeException("Impossible diapason");
        }

        int delta = getDelta(number, min, max);
        if (delta != 0) {
            throw new RuleException(delta);
        }
    }

    private int setMin(int min) {
        int rest = min % divider;
        if (rest == 0) return min;
        return min - rest + divider;
    }

    private int setMax(int max) {
        int rest = max % divider;
        if (rest == 0) return max;
        return max - rest;
    }

    private int getDelta(int number, int min, int max) {
        if (number > max) return max - number;
        if (number < min) return min - number;

        int modulo = -(number % divider);
        int delta = divider + modulo;
        if (-modulo < delta) {
            return modulo;
        }else return delta;
    }
}
