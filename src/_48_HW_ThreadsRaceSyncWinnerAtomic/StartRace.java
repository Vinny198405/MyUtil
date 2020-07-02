package _48_HW_ThreadsRaceSyncWinnerAtomic;

import _39_HW_MenuItemsInputOutput.menu.*;

import java.util.concurrent.atomic.AtomicInteger;

public class StartRace implements Item {
    InputOutput inputOutput;

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
        Racer[] racers = new Racer[nRacers];
        Race race = new Race(2, 5, distance);
        race.winnerId = new AtomicInteger(0);
        startRacers(racers, race);
        waitRacers(racers);
        System.out.printf("Congratulations to thread #%d - winner\n", race.winnerId.get());

    }

    private void waitRacers(Racer[] racers) {
        for (Racer racer : racers) {
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
