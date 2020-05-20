package DronesApplication;

import javax.swing.*;
import java.util.*;
import java.util.stream.IntStream;

public class DronesAppl {
    private static final int N_DRONES = 20;
    private static final int N_HEIGHTS = 15;
    private static final int MIN_PASSENGER_TIME = 1;
    private static final int MAX_PASSENGER_TIME = 15;
    private static final int MODEL_TIME = 240;
    private static Drone[] drones;
    private static List<Drone> dronesInAir;
    private static List<Drone> dronesInQueue;
    private static String[] heights;
    private static HashMap<String, Integer> heightsCounts = new HashMap<>();

    public static void main(String[] args) {
        preProcessing();
        play();
        postProcessing();
        testApplication();
        displayResults();
    }

    private static void testApplication() {
        int totalPassengers = 0;
        for (Drone drone : drones) {
            totalPassengers += drone.getPassengers();
            int totalTime = drone.getTotalAirIterations() + drone.getTotalQueueIterations();
            if (totalTime != MODEL_TIME) {
                throw new RuntimeException("Wrong total time. There is: " + totalTime +
                        "; should be: " + MODEL_TIME);
            }
        }
        if (totalPassengers != totalFlights()) {
            throw new RuntimeException("ERROR number of flights");
        }
    }

    private static void displayResults() {
        displayDrone();
        displayHeight();
        displayMostUsedHeights();
    }

    private static void displayDrone() {
        Arrays.stream(drones)
                .forEach(d -> System.out.printf("Drone: %d; was in air: %d minutes. It transferred: %d passengers; It was in waiting queue: %d minutes \n",
                        d.getSeqNumber(), d.getTotalAirIterations(), d.getPassengers(), d.getTotalQueueIterations()));
    }

    private static void displayHeight() {
        heightsCounts.forEach((key, value) -> System.out.println(key + ": " + value + " flights"));
    }

    private static void displayMostUsedHeights() {
        List<String> res = new ArrayList<>();
        int maxValueInMap = (Collections.max(heightsCounts.values()));
        heightsCounts.forEach((key, value) -> {
            if (value == maxValueInMap) res.add(key);
        });
        System.out.println("The most used heights: " + res);
    }

    private static int totalFlights() {
        return heightsCounts.values().stream().reduce(0, Integer::sum);
    }

    private static void postProcessing() {
        setTotalQueueEnd();
        setTotalAirEnd();
    }

    private static void setTotalAirEnd() {
        int count = dronesInAir.size();
        for (int i = 0; i < count; i++) {
            Drone drone = dronesInAir.remove(0);
            drone.setTotalAirIterations((MODEL_TIME - drone.getStartIteration())
                    + drone.getTotalAirIterations());
        }
    }

    private static void setTotalQueueEnd() {
        int count = dronesInQueue.size();
        for (int i = 0; i < count; i++) {
            Drone drone = dronesInQueue.remove(0);
            drone.setTotalQueueIterations((MODEL_TIME - drone.getStartQueueIteration())
                    + drone.getTotalQueueIterations());
        }
    }

    private static void play() {
        for (int i = 1; i <= MODEL_TIME; i++) {
            List<String> freedHeights = landingOnIteration(i);
            takingOffOnIteration(i, freedHeights);
        }
    }

    private static void takingOffOnIteration(int nIteration, List<String> freedHeights) {
        int count = freedHeights.size();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                Drone drone = dronesInQueue.remove(0);
                takeOff(drone, nIteration, freedHeights.remove(0));
                drone.setTotalQueueIterations((nIteration - drone.getStartQueueIteration())
                        + drone.getTotalQueueIterations());
            }
        }
    }

    private static List<String> landingOnIteration(int nIteration) {
        List<String> res = new ArrayList<>();
        Iterator<Drone> it = dronesInAir.iterator();
        while (it.hasNext()) {
            Drone drone = it.next();

            if (drone.getFinishIteration() == nIteration) {
                it.remove();
                int timeInAir =
                        drone.getFinishIteration() - drone.getStartIteration();
                drone.setTotalAirIterations
                        (drone.getTotalAirIterations() + timeInAir);
                putInQueue(drone, nIteration);
                res.add(drone.getHeight());
            }
        }
        return res;
    }

    private static void preProcessing() {
        createDrones();
        initializeInAirInQueue();
        initializeHeight();
        startDronesInAir();
        startDronesInQueue();
    }

    private static void initializeHeight() {
        heights = new String[N_HEIGHTS];
        for (int i = 0; i < N_HEIGHTS; i++) {
            heights[i] = "height" + (i + 1);
        }
    }

    private static void initializeInAirInQueue() {
        dronesInAir = new LinkedList<Drone>();
        dronesInQueue = new LinkedList<Drone>();
    }

    private static void startDronesInQueue() {
        for (int i = N_HEIGHTS; i < N_DRONES; i++) {
            putInQueue(drones[i], 0);
        }
    }

    private static void putInQueue(Drone drone, int nIteration) {
        dronesInQueue.add(drone);
        drone.setStartQueueIteration(nIteration);
    }

    private static void startDronesInAir() {
        for (int i = 0; i < N_HEIGHTS; i++) {
            takeOff(drones[i], 0, heights[i]);
        }
    }

    private static void takeOff(Drone drone, int nIteration, String height) {
        dronesInAir.add(drone);
        drone.setStartIteration(nIteration);
        int iterationsInAir = getIterationsInAir();
        drone.setFinishIteration(nIteration + iterationsInAir);
        drone.setHeight(height);
        drone.setPassengers(drone.getPassengers() + 1); // добовляем пассажира
        heightsCounts.merge(height, 1, Integer::sum);
    }

    private static int getIterationsInAir() {
        return (int) (MIN_PASSENGER_TIME +
                Math.random() * (MAX_PASSENGER_TIME -
                        MIN_PASSENGER_TIME + 1));
    }

    private static void createDrones() {
        drones = IntStream.range(0, N_DRONES)
                .mapToObj(i -> new Drone(i + 1))
                .toArray(Drone[]::new);
    }
}
