package _48_HW_ThreadsRaceResultsTabLock;

import _39_HW_MenuItemsInputOutput.menu.*;

import java.time.Instant;
import java.util.ArrayList;

public class StartRace implements Item {
    private InputOutput inputOutput;

    public StartRace(InputOutput inputOutput) {
        super();
        this.inputOutput = inputOutput;
    }

    @Override
    public String displayName() {
        return "start race";
    }

    @Override
    public void perform() {
        int nRacers = inputOutput.inputInteger("Enter number of racers", 2, 100);
        int distance = inputOutput.inputInteger("Enter distance", 10, 1000);
        Racer [] racers = new Racer[nRacers];
        Race race = new Race(2, 5, distance);
        race.results = new ArrayList<>();
        race.start = Instant.now();
        startRacers(racers, race);
        waitRacers(racers);
        displayResults(race);

    }
    private void displayResults(Race race) {
        System.out.println("           Results Table");
        System.out.println("place\tracer number\ttime");
        int size = race.results.size();
        for (int i = 0; i < size; i++) {
            Racer racer = race.results.get(i);
            System.out.printf("  %d\t\t     %d\t       %d\n",
                    i + 1, racer.getThreadId(), racer.getRunTime());
        }
    }

    private void waitRacers(Racer[] racers) {
        for(Racer racer: racers) {
            try {
                racer.join();
            } catch (InterruptedException e) {
            }
        }
    }

    private void startRacers(Racer[] racers, Race race) {
        for (int i = 0; i < racers.length; i++) {
            racers[i] = new Racer(i + 1, race);
            racers[i].start();
        }
    }
}

