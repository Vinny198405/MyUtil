package _43_CW_Printers;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class PrintersControllers {
    private static final int N_SYMBOLS = 100;

    public static void main(String[] args) throws InterruptedException {
        Printer printer1 = new Printer('#', N_SYMBOLS);
        Printer printer2 = new Printer('+', N_SYMBOLS);
        Instant start = Instant.now();
        printer1.start();
        printer2.start();
        printer1.join();
        printer2.join();
        Instant finish = Instant.now();
        System.out.printf("running time = %d\n",
                ChronoUnit.MILLIS.between(start, finish));

    }
}
