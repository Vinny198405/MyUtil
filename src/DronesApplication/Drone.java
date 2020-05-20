package DronesApplication;

public class Drone {

    private int seqNumber;
    private String height;
    private int nPassengers;  //колличество пассажиров
    private int startIteration; // начальная итерация воздуха
    private int finishIteration; // количество итераций в воздухе
    private int startQueueIteration;
    private int totalAirIterations;
    private int totalQueueIterations;
    public Drone(int seqNumber) {
        this.seqNumber = seqNumber;
    }

    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public int getPassengers() {
        return nPassengers;
    }
    public void setPassengers(int nPassengers) {
        this.nPassengers = nPassengers;
    }
    public int getStartIteration() {
        return startIteration;
    }
    public void setStartIteration(int startIteration) {
        this.startIteration = startIteration;
    }
    public int getFinishIteration() {
        return finishIteration;
    }
    public void setFinishIteration(int finishIteration) {
        this.finishIteration = finishIteration;
    }
    public int getStartQueueIteration() {
        return startQueueIteration;
    }
    public void setStartQueueIteration(int startQueueIteration) {
        this.startQueueIteration = startQueueIteration;
    }
    public int getTotalAirIterations() {
        return totalAirIterations;
    }
    public void setTotalAirIterations(int totalAirIterations) {
        this.totalAirIterations = totalAirIterations;
    }
    public int getTotalQueueIterations() {
        return totalQueueIterations;
    }
    public void setTotalQueueIterations(int totalQueueIterations) {
        this.totalQueueIterations = totalQueueIterations;
    }
    public int getSeqNumber() {
        return seqNumber;
    }

}

