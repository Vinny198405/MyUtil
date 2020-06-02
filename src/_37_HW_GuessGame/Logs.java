package _37_HW_GuessGame;

public class Logs {
    private int cows;
    private int bulls;
    private String number;

    public Logs() {
    }

    public Logs(int cows, int bulls, String number) {
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

    @Override
    public String toString() {
        return "{cows:" + cows + "; bulls:" + bulls + "; number:" + number + "}";
    }
}
