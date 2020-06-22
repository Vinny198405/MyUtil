package _45_HW_PrintersShcedulingPortiion;

import static _45_HW_PrintersShcedulingPortiion.PrinterController.threadId;

public class Printer extends Thread {
    private String name;
    private int nNumbers;
    private int nPortions;
    private volatile boolean running = true;

    public Printer(String name, int nNumbers, int nPortions) {
        this.name = name;
        this.nNumbers = nNumbers;
        this.nPortions = nPortions;
    }

    @Override
    public void run() {
        while (running) {
            if (threadId.equals(name)) {
                for (int i = 0; i < nPortions; i++) {
                    if (nNumbers-- > 0) {
                        System.out.print(name);
                    }
                    if (!running) break;
                }
                System.out.println();
                if (nNumbers > 0) {
                    PrinterController.finishedPrinting();
                }
                else {
                    PrinterController.finishedPrinting();
                    running = false;
                }
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
