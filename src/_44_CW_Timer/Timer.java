package _44_CW_Timer;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer extends Thread {
    private int period;

    public Timer(int period) {
        setDaemon(true);
        this.period = period;
    }

    public Timer() {
        this(1000);
    }
    @Override
    public void run() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        while(true) {
            System.out.println(LocalTime.now().format(dtf));
            try {
                sleep(period);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
