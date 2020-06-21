package _45_CW_PrintersWithEnding;

public class Printer extends Thread {
    private String symbol;
    private volatile boolean running = true;
    private volatile boolean nextStep = true;

    public Printer(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public void run() {
        while (running) {
            for (char s : symbol.toCharArray()) {
                while (nextStep) {
                    System.out.println("thread " + this.getId() + " - " + s);
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!running) break;
                }
                if (!running) break;
                nextStep = true;
            }
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setNextStep(boolean nextStep) {
        this.nextStep = nextStep;
    }
}
