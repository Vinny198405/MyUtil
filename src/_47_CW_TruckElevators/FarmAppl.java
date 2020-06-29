package _47_CW_TruckElevators;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class FarmAppl {
    private static final int N_TRUCKS = 5;
    private static final int LOAD = 5;
    private static final int N_LOADS = 500;

    public static void main(String[] args) {
        Truck[] trucks = new Truck[N_TRUCKS];
        Instant start = Instant.now();
        srartTrucks(trucks);
        waitingForFinish(trucks);
        Instant finish = Instant.now();
        System.out.printf("Running time: %d; elevator1: %d; elevator2: %d;", ChronoUnit.MILLIS.between(start, finish),
                Truck.getElevator1(), Truck.getElevator2());

    }

    private static void waitingForFinish(Truck[] trucks) {
        for (Truck truck:trucks){
            try {
                truck.join();
            } catch (InterruptedException e) {

            }
        }
    }

    private static void srartTrucks(Truck[] trucks) {
        for (int i = 0; i < trucks.length; i++) {
            trucks[i] = new Truck(LOAD, N_LOADS);
            trucks[i].start();
        }
    }
}
