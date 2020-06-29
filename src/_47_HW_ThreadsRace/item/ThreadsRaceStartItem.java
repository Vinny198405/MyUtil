package _47_HW_ThreadsRace.item;

import _39_HW_MenuItemsInputOutput.menu.InputOutput;
import _47_HW_ThreadsRace.ThreadsRace;

import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ThreadsRaceStartItem extends ThreadsRaceItem {

    public ThreadsRaceStartItem(ThreadsRace threadsRace, InputOutput inputOutput) {
        super(threadsRace, inputOutput);
    }

    @Override
    public String displayName() {
        return "Start Threads Race Game";
    }

    @Override
    public void perform() {
        ThreadsRace.setWinner(null);
        ThreadsRace.setWinnerMap(new HashMap<>());
        int threads = inputOutput.inputInteger("Enter number of threads [2-1000]:", 2, 1000);
        int distance = inputOutput.inputInteger("Enter number of distance [2-1000]:", 2, 1000);
        try {
            startThreads(threads, distance);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Congratulations to winner " + ThreadsRace.getWinner());
        printMapListWinners();
    }

    private void printMapListWinners() {
        Map<String, Instant> winnerMap = ThreadsRace.getWinnerMap();
        Map<String, Instant> result = winnerMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        System.out.println();
        result.forEach((k, v) -> System.out.println(k + " " + v));
    }

    private void startThreads(int threads, int distance) throws InterruptedException {
        ThreadsRace[] threadsRace = new ThreadsRace[threads];
        for (int i = 0; i < threads; i++) {
            threadsRace[i] = new ThreadsRace("Thread" + i, distance);
            threadsRace[i].start();
        }
        for (ThreadsRace sh : threadsRace) {
            sh.join();
        }
    }
}
