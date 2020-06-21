package _45_CW_PrintersWithEnding;

import java.util.Scanner;

public class PrinterController {
    private static Printer[] printers;
    private static String[] strings = {"Hello", "Java", "Delphi"};

    public static void main(String[] args) {
        startTh(strings);
        Scanner scanner = new Scanner(System.in);
        String line;
        while (true) {
            line = scanner.nextLine();
            if (line.equals("q")) break;
            boolean wrong = true;
            for (Printer printer : printers) {
                if (line.equals(Long.toString(printer.getId()))) {
                    printer.setNextStep(false);
                    wrong = false;
                }
            }
            if (wrong) System.out.println("wrong thread id");
        }
        stopTh();
    }

    private static void stopTh() {
        for (Printer printer : printers) {
            printer.setRunning(false);
        }
    }

    private static void startTh(String[] strings) {
        int length = strings.length;
        printers = new Printer[length];
        for (int i = 0; i < length; i++) {
            printers[i] = new Printer(strings[i]);
            printers[i].start();
        }
    }
}
