package BullsAndCows;

public class Log {
    private int cows;
    private int bulls;
    private String number;

    public Log() {
    }

    public Log(int cows, int bulls, String number) {
        this.cows = cows;
        this.bulls = bulls;
        this.number = number;
    }

    public int getCows() {
        return cows;
    }

    public int getBulls() {
        return bulls;
    }

    public String getNumber() {
        return number;
    }
}
