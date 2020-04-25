package Exceptions;

public class RuleException extends Exception {
    private int delta;

    public RuleException(int delta) {
        this.delta = delta;
    }

    public int getDelta() {
        return delta;
    }
}
