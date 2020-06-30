package _45_HW_PrintersShcedulingPortiion;

public class PrinterController {
    private static Printer[] printers;
    private static int N_PRINTER = 4;
    private static int N_NUMBERS = 100;
    private static int N_PORTIONS = 10;
    private static int[] order = {1, 3, 2, 0};

    public static void main(String[] args) {
        startThread();
        setPrinters();
        printers[order[0]].interrupt();
    }

    private static void setPrinters() {
        int length = order.length - 1;
        for (int i = 0; i < length; i++) {
            printers[order[i]].setThread(printers[order[i + 1]]);
        }
        printers[order[length]].setThread(printers[order[0]]);
    }

    private static void startThread() {
        printers = new Printer[N_PRINTER];
        for (int i = 0; i < N_PRINTER; i++) {
            printers[i] = new Printer(i, N_NUMBERS, N_PORTIONS);
            printers[i].start();
        }
    }
}
