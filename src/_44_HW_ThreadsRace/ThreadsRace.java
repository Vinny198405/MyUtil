package _44_HW_ThreadsRace;

public class ThreadsRace extends Thread{
    private int period;
    public String name;

    public ThreadsRace(String name,int period) {
        this.period = period;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            sleep(period);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadsRaceAppl.done();
    }

}
