package _45_HW_PrintersShcedulingPortiion;

public class PrinterController {
    private static Printer[] printers;
    private static int N_PRINTER = 4;
    private static int N_NUMBERS = 100;
    private static int N_PORTIONS = 10;
    private static int index = 0;
    private static int[] order = {0, 1, 2, 3};

    public static void main(String[] args) {
        startThread();
        printThread(order[index++]);
    }

    public static void finishedPrinting() {
        if (index == order.length) index = 0;
        printThread(order[index++]);
    }

    private static void printThread(int order) {
        printers[order].interrupt();
    }

    private static void startThread() {
        printers = new Printer[N_PRINTER];
        for (int i = 0; i < N_PRINTER; i++) {
            printers[i] = new Printer(order[i], N_NUMBERS, N_PORTIONS);
            printers[i].start();
        }
    }
}
